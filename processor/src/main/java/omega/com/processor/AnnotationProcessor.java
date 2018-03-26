package omega.com.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import omega.com.annotations.OmegaActivity;
import omega.com.annotations.OmegaExtra;
import omega.com.annotations.OmegaExtraModel;
import omega.com.annotations.OmegaFragment;
import omega.com.annotations.OmegaService;

import static omega.com.processor.StringUtils.formatMethodName;
import static omega.com.processor.StringUtils.isEmpty;
import static omega.com.processor.StringUtils.isSuitableName;
import static omega.com.processor.StringUtils.replaceFirstToLowerCase;
import static omega.com.processor.StringUtils.replaceFirstToUpperCase;
import static omega.com.processor.SupportUtils.isDefaultOrProtected;
import static omega.com.processor.SupportUtils.isInstanceOfSuperclass;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"omega.com.annotations.OmegaActivity",
                           "omega.com.annotations.OmegaService",
                           "omega.com.annotations.OmegaExtra",
                           "omega.com.annotations.OmegaExtraModel"})
public class AnnotationProcessor extends AbstractProcessor {

    private static final ClassName sClassIntent = ClassName.get("android.content", "Intent");
    private static final ClassName sClassContext = ClassName.get("android.content", "Context");
    private static final ClassName sClassActivity = ClassName.get("android.app", "Activity");
    private static final ClassName sClassFragment = ClassName.get("android.app", "Fragment");
    private static final ClassName sClassSupportFragment = ClassName.get("android.support.v4.app", "Fragment");

    private static final ClassName sClassService = ClassName.get("android.app", "Service");
    private static final ClassName sClassBundle = ClassName.get("android.os", "Bundle");

    private static final ClassName sClassBaseBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder.builders", "BaseActivityBuilder");
    private static final ClassName sClassBaseServiceBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder.builders", "BaseServiceBuilder");
    private static final ClassName sClassOmegaIntentBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder", "OmegaIntentBuilder");
    private static final ClassName sClassBundleBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder.bundle", "BundleBuilder");

    private static final String PACKAGE_NAME = "com.omega_r.libs.omegaintentbuilder";
    private static final String FRAGMENT_BUILDER_PACKAGE_NAME = "com.omega_r.libs.omegafragmentbuilder";
    private static final String APP_ACTIVITY_INTENT_BUILDER = "AppActivityIntentBuilder";
    private static final String APP_SERVICE_INTENT_BUILDER = "AppServiceIntentBuilder";
    private static final String APP_OMEGA_INTENT_BUILDER = "AppOmegaIntentBuilder";
    private static final String APP_OMEGA_FRAGMENT_BUILDER = "AppOmegaFragmentBuilder";
    private static final ClassName sClassAppOmegaIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_OMEGA_INTENT_BUILDER);
    private static final ClassName sClassAppActivityIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_ACTIVITY_INTENT_BUILDER);
    private static final ClassName sClassAppServiceIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_SERVICE_INTENT_BUILDER);

    private static final String OMEGA_GENERATED = "$OmegaGenerated";
    private static final String SET = "set";
    private static final String MODEL = "model";
    private static final String VALUE = "value";

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElements;
    private RoundEnvironment mRoundEnvironment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
        mElements = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }
        mRoundEnvironment = roundEnv;
        return process();
    }

    private boolean process() {
        try {
            List<Element> activityElements = getCorrectElements(OmegaActivity.class, TypeEnum.ACTIVITY);
            List<MethodSpec> activityBuildersList = generateBuilderMethods(activityElements, TypeEnum.ACTIVITY);

            List<Element> serviceElements = getCorrectElements(OmegaService.class, TypeEnum.SERVICE);
            List<MethodSpec> serviceBuildersList = generateBuilderMethods(serviceElements, TypeEnum.SERVICE);

            TypeSpec activityIntentBuilder = TypeSpec.classBuilder(APP_ACTIVITY_INTENT_BUILDER)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                    .addMethod(generateClassConstructorMethod(true, false).build())
                    .addMethods(activityBuildersList)
                    .build();

            TypeSpec serviceIntentBuilder = TypeSpec.classBuilder(APP_SERVICE_INTENT_BUILDER)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                    .addMethod(generateClassConstructorMethod(true, false).build())
                    .addMethods(serviceBuildersList)
                    .build();

            JavaFile.builder(PACKAGE_NAME, activityIntentBuilder).build()
                    .writeTo(mFiler);
            JavaFile.builder(PACKAGE_NAME, serviceIntentBuilder).build()
                    .writeTo(mFiler);
            generateAppOmegaIntentBuilder();
            generateAppOmegaFragmentBuilder();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void generateAppOmegaIntentBuilder() throws IOException {
        TypeSpec omegaIntentBuilder = TypeSpec.classBuilder(APP_OMEGA_INTENT_BUILDER)
                .addModifiers(Modifier.PUBLIC)
                .superclass(sClassOmegaIntentBuilder)
                .addMethod(generateClassConstructorMethod(true, true).build())
                .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.methodBuilder("from")
                        .returns(sClassAppOmegaIntentBuilderClass)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(sClassContext, "context")
                        .addStatement("return new $T(context)", sClassAppOmegaIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("appActivity")
                        .returns(sClassAppActivityIntentBuilderClass)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return new $T(context)", sClassAppActivityIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("appService")
                        .returns(sClassAppServiceIntentBuilderClass)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return new $T(context)", sClassAppServiceIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("inject")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ParameterSpec.builder(sClassActivity, "activity").build())
                        .addStatement("AppActivityIntentBuilder.inject(activity)", sClassAppActivityIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("inject")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ParameterSpec.builder(sClassService, "service").build())
                        .addParameter(ParameterSpec.builder(sClassIntent, "intent").build())
                        .addStatement("AppServiceIntentBuilder.inject(service, intent)", sClassAppServiceIntentBuilderClass)
                        .build())
                .build();

        JavaFile.builder(PACKAGE_NAME, omegaIntentBuilder)
                .build()
                .writeTo(mFiler);
    }

    private void generateAppOmegaFragmentBuilder() throws IOException {
        List<Element> fragmentElements = getCorrectElements(OmegaFragment.class, TypeEnum.FRAGMENT);
        List<MethodSpec> fragmentBuildersList = generateBuilderMethods(fragmentElements, TypeEnum.FRAGMENT);

        List<Element> supportFragmentElements = getCorrectElements(OmegaFragment.class, TypeEnum.SUPPORT_FRAGMENT);
        fragmentBuildersList.addAll(generateBuilderMethods(supportFragmentElements, TypeEnum.SUPPORT_FRAGMENT));

        TypeSpec omegaFragmentBuilder = TypeSpec.classBuilder(APP_OMEGA_FRAGMENT_BUILDER)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethods(fragmentBuildersList)
                .build();
        JavaFile.builder(FRAGMENT_BUILDER_PACKAGE_NAME, omegaFragmentBuilder)
                .build()
                .writeTo(mFiler);
    }

    private List<Element> getCorrectElements(Class<? extends Annotation> annotation, TypeEnum type) {
        Set<? extends Element> annotatedElements = mRoundEnvironment.getElementsAnnotatedWith(annotation);
        List<Element> correctElements = new ArrayList<>();

        for (Element element : annotatedElements) {
            if (isAnnotationCorrect(element, type)) correctElements.add(element);
        }
        return correctElements;
    }

    private boolean isAnnotationCorrect(Element element, TypeEnum type) {
        if (element.getKind() != ElementKind.CLASS) {
            String message = "Annotation" + type.getShortName() + "can only be used for classes!";
            mMessager.printMessage(Diagnostic.Kind.ERROR, message);
            return false;
        }
        switch (type) {
            case FRAGMENT:
            case SUPPORT_FRAGMENT:
                if (!isInstanceOfSuperclass(element, TypeEnum.FRAGMENT.className, TypeEnum.SUPPORT_FRAGMENT.className)) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR,
                            element.getSimpleName().toString() + " should be instanceOf "
                                    + TypeEnum.FRAGMENT.className + " or " + TypeEnum.SUPPORT_FRAGMENT.className);
                    return false;
                }
                return isInstanceOfSuperclass(element, type.className);
            default:
                if (!isInstanceOfSuperclass(element, type.className)) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR,
                            element.getSimpleName().toString() + " should be instanceOf " + type.className);
                    return false;
                }
                break;
        }

        return true;
    }

    private List<MethodSpec> generateBuilderMethods(List<Element> elements, TypeEnum type) {
        List<Element> list = new ArrayList<>();
        List<ClassName> classNameList = new ArrayList<>();

        for (Element element : elements) {
            ClassName className = ClassName.get(mElements.getPackageOf(element).getQualifiedName().toString(),
                    element.getSimpleName().toString());
            if (generateBuilderClass(element, className, type)) {
                list.add(element);
                classNameList.add(className);
            }
        }

        List<MethodSpec> methodList = new ArrayList<>();
        methodList.add(generateInjectMethod(classNameList, type));

        for (Element element : list) {
            methodList.add(generateReturnBuilderMethod(element, type));
        }

        return methodList;
    }

    private boolean generateBuilderClass(Element element, ClassName elementClassName, TypeEnum type) {
        String builderClassName = element.getSimpleName().toString() + "Builder";
        String packageName = mElements.getPackageOf(element).toString();

        switch (type) {
            case FRAGMENT:
            case SUPPORT_FRAGMENT:
                return generateFragmentBuilderClass(type, element, elementClassName, builderClassName, packageName);
            case ACTIVITY:
                return generateBuilderClass(type, element, elementClassName, builderClassName, packageName, sClassBaseBuilder);
            case SERVICE:
                return generateBuilderClass(type, element, elementClassName, builderClassName, packageName, sClassBaseServiceBuilder);
        }
        return false;
    }

    private boolean generateBuilderClass(TypeEnum type, Element element,
                                         ClassName elementClassName, String builderClassName,
                                         String packageName, ClassName superClass) {
        TypeSpec intentBuilder = TypeSpec.classBuilder(builderClassName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(superClass)
                .addField(sClassIntent, "intent", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(generateClassConstructorMethod(false, true)
                        .addStatement("intent = new Intent(context, $T.class)", elementClassName)
                        .build())
                .addMethod(generateCreateIntentMethod(elementClassName))
                .addMethods(generateBuilderMethods(element, ClassName.get(packageName, builderClassName), type))
                .build();
        try {
            JavaFile.builder(packageName, intentBuilder)
                    .build()
                    .writeTo(mFiler);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean generateFragmentBuilderClass(TypeEnum type, Element element, ClassName elementClassName,
                                                 String builderClassName, String packageName) {
        FieldSpec bundleBuilderField = FieldSpec.builder(sClassBundleBuilder, "bundleBuilder")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .initializer("new $T()", sClassBundleBuilder)
                .build();
        TypeSpec fragmentBuilder = TypeSpec.classBuilder(builderClassName)
                .addModifiers(Modifier.PUBLIC)
                .addField(bundleBuilderField)
                .addMethod(MethodSpec.methodBuilder("createFragment")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("$T fragment = new $T()", elementClassName, elementClassName)
                        .addStatement("fragment.setArguments(bundleBuilder.create())", elementClassName)
                        .addStatement("return fragment")
                        .returns(elementClassName)
                        .build())
                .addMethods(generateBuilderMethods(element, ClassName.get(packageName, builderClassName), type))
                .build();
        try {
            JavaFile.builder(packageName, fragmentBuilder)
                    .build()
                    .writeTo(mFiler);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private MethodSpec generateReturnBuilderMethod(Element element, TypeEnum type) {
        String methodName = element.getSimpleName().toString();
        String returnClassName = methodName + "Builder";
        methodName = replaceFirstToLowerCase(methodName);
        String packageName = mElements.getPackageOf(element).toString();

        MethodSpec.Builder builder = MethodSpec.methodBuilder(methodName);
        switch (type) {
            case FRAGMENT:
            case SUPPORT_FRAGMENT:
                builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                       .addStatement("return new " + returnClassName + "()");
                break;
            default:
                builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                       .addStatement("return new " + returnClassName + "(context)");
                break;
        }

        return builder.returns(ClassName.get(packageName, returnClassName))
                .build();
    }

    private MethodSpec generateInjectMethod(List<ClassName> list, TypeEnum type) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ParameterSpec.builder(type.getClassName(), type.getShortName()).build());
        switch (type) {
            case SERVICE:
                builder.addParameter(ParameterSpec.builder(sClassIntent, "intent").build());
                break;
            default:
                // nothing
                break;
        }
        CodeBlock.Builder codeBuilder = CodeBlock.builder();
        for (ClassName clsName : list) {
            String className = clsName.simpleName();
            ClassName fullClassName = ClassName.get(clsName.packageName(), className);
            codeBuilder.beginControlFlow("if(" + type.getShortName() + " instanceof $T)", fullClassName);
            switch (type) {
                case SERVICE:
                    codeBuilder.addStatement(className + "Builder.inject((" + className + ")" + type.getShortName() + ", intent)", className)
                            .addStatement("return")
                            .endControlFlow();
                    break;
                default:
                    codeBuilder.addStatement(className + "Builder.inject((" + className + ")" + type.getShortName() + ")", className)
                            .addStatement("return")
                            .endControlFlow();
                    break;
            }
        }
        builder.addCode(codeBuilder.build());

        return builder.build();
    }

    private MethodSpec.Builder generateClassConstructorMethod(boolean withContextField, boolean withSuper) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(sClassContext, "context");
        if (withSuper) {
            builder.addStatement("super(context)");
        }
        if (withContextField) {
            builder.addStatement("this.$N = $N", "context", "context");
        }
        return builder;
    }

    private MethodSpec generateCreateIntentMethod(ClassName className) {
        return MethodSpec.methodBuilder("createIntent")
                .returns(sClassIntent)
                .addStatement("return intent", className)
                .addModifiers(Modifier.PUBLIC)
                .build();
    }

    private List<MethodSpec> generateBuilderMethods(Element parentElement, ClassName returnClassname, TypeEnum type) {
        List<MethodSpec> methodSpecList = new ArrayList<>();
        List<? extends Element> subList = parentElement.getEnclosedElements();

        if (subList == null || subList.isEmpty()) {
            return methodSpecList;
        }

        String valueName = replaceFirstToLowerCase(((DeclaredType) parentElement.asType()).asElement().getSimpleName().toString());
        MethodSpec.Builder injectMethodBuilder = generateSubInjectMethod(type, parentElement, valueName);

        CodeBlock.Builder injectMethodCodeBuilder = CodeBlock.builder();
        injectMethodCodeBuilder.beginControlFlow("if(extras != null) ");

        for (Element subElement : subList) {
            generateExtraMethod(type, methodSpecList, injectMethodCodeBuilder, parentElement,
                    returnClassname, subElement, "", valueName, false);

            OmegaExtraModel extraModelAnnotation = subElement.getAnnotation(OmegaExtraModel.class);
            if (extraModelAnnotation != null) {
                if (subElement.getKind() != ElementKind.FIELD) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR, "Annotation OmegaExtraModel can only be used for classes!");
                } else if (subElement.getModifiers().contains(Modifier.PRIVATE)) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR, subElement.getSimpleName() + " in " + parentElement.getSimpleName() + " can't be private;");
                } else {
                    String prefix = extraModelAnnotation.prefix();
                    if (!isEmpty(prefix) && isSuitableName(prefix)) {
                        prefix = replaceFirstToLowerCase(prefix);
                    } else {
                        prefix = "";
                    }
                    List<? extends Element> enclosedElements = ((DeclaredType) subElement.asType()).asElement().getEnclosedElements();

                    generateProtectedBackdoorClass(subElement, enclosedElements);

                    for (Element childElement : enclosedElements) {
                        generateExtraMethod(type, methodSpecList, injectMethodCodeBuilder, subElement,
                                returnClassname, childElement, prefix, valueName, true);
                    }
                }
            }
        }

        injectMethodCodeBuilder.endControlFlow();
        injectMethodBuilder.addCode(injectMethodCodeBuilder.build());
        methodSpecList.add(injectMethodBuilder.build());
        return methodSpecList;
    }

    private MethodSpec.Builder generateSubInjectMethod(TypeEnum type, Element element, String valueName) {
        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ParameterSpec.builder(ClassName.get(element.asType()), valueName).build());

        switch (type) {
            case ACTIVITY:
                injectMethodBuilder.addStatement("$T extras = " + valueName + ".getIntent().getExtras()", sClassBundle);
                break;
            case SERVICE:
                injectMethodBuilder.addStatement("if(intent == null) return")
                        .addParameter(ParameterSpec.builder(sClassIntent, "intent").build())
                        .addStatement("$T extras = intent.getExtras()", sClassBundle);
                break;
            case FRAGMENT:
            case SUPPORT_FRAGMENT:
                injectMethodBuilder.addStatement("if(" + valueName + " == null) return")
                        .addStatement("if(" + valueName + ".getArguments() == null) return")
                        .addStatement("$T extras = " + valueName + ".getArguments()", sClassBundle);
                break;
        }

        return injectMethodBuilder;
    }

    private void generateProtectedBackdoorClass(Element omegaModelElement, List<? extends Element> subElements) {
        List<Element> protectedElements = new ArrayList<>();

        for (Element element : subElements) {
            OmegaExtra annotation = element.getAnnotation(OmegaExtra.class);
            if (annotation != null && isDefaultOrProtected(element)) {
                protectedElements.add(element);
            }
        }
        if (!protectedElements.isEmpty()) {
            Element parentElement = ((DeclaredType) omegaModelElement.asType()).asElement();
            String className = parentElement.getSimpleName().toString();
            String packageName = mElements.getPackageOf(parentElement).toString();

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className + OMEGA_GENERATED)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

            for (Element element : protectedElements) {
                String elementName = element.getSimpleName().toString();
                String methodName = SET + replaceFirstToUpperCase(elementName);

                ParameterSpec modelParameterSpec = ParameterSpec.builder(ClassName.get(parentElement.asType()), MODEL).build();
                ParameterSpec valueParameterSpec = ParameterSpec.builder(ClassName.get(element.asType()), VALUE).build();

                classBuilder.addMethod(MethodSpec.methodBuilder(methodName)
                        .addParameter(modelParameterSpec)
                        .addParameter(valueParameterSpec)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addStatement(MODEL + "." + elementName + " = " + VALUE).build());
            }
            try {
                JavaFile.builder(packageName, classBuilder.build())
                        .build()
                        .writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateExtraMethod(TypeEnum type,
                                     List<MethodSpec> methodSpecList,
                                     CodeBlock.Builder injectMethodCodeBuilder,
                                     Element parentElement,
                                     ClassName returnClassName,
                                     Element element,
                                     String methodNamePrefix,
                                     String valueName,
                                     boolean fromOmegaModel) {
        OmegaExtra annotation = element.getAnnotation(OmegaExtra.class);
        if (annotation != null) {
            String methodName = formatMethodName(annotation);
            String key = annotation.value();

            if (isEmpty(methodNamePrefix)) {
                methodName = replaceFirstToLowerCase(isSuitableName(methodName) ? methodName : element.toString());
            } else {
                methodName = methodNamePrefix + replaceFirstToUpperCase(isSuitableName(methodName) ? methodName : element.toString());
            }
            if (StringUtils.isEmpty(key)) {
                key = element.toString();
            }

            ParameterSpec parameter = ParameterSpec.builder(ClassName.get(element.asType()), "value").build();

            MethodSpec.Builder putExtraMethodBuilder = MethodSpec.methodBuilder(methodName)
                    .returns(returnClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(parameter);

            switch (type) {
                case FRAGMENT:
                case SUPPORT_FRAGMENT:
                    putExtraMethodBuilder.addStatement("bundleBuilder.putExtra(\"" + key + "\", " + VALUE + ")");
                    break;
                default:
                    putExtraMethodBuilder.addStatement("intent.putExtra(\"" + key + "\", " + VALUE + ")");
                    break;
            }
            methodSpecList.add(putExtraMethodBuilder.addStatement("return this", returnClassName).build());

            if (element.getModifiers().contains(Modifier.PRIVATE)) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, element.getSimpleName() + " in " + parentElement.getSimpleName() + " can't be private;");
            } else {
                injectMethodCodeBuilder.beginControlFlow("if(extras.containsKey(\"" + key + "\"))");
                if (fromOmegaModel) {
                    if (isDefaultOrProtected(element)) {
                        Element omegaModelElement = ((DeclaredType) parentElement.asType()).asElement();
                        String shortClassName = omegaModelElement.getSimpleName().toString() + OMEGA_GENERATED;
                        String packageName = mElements.getPackageOf(omegaModelElement).toString();
                        ClassName backdoorClassName = ClassName.get(packageName, shortClassName);
                        String method = SET + replaceFirstToUpperCase(element.getSimpleName().toString());

                        injectMethodCodeBuilder.add("$T." + method + "(" + valueName + "."
                                + parentElement.toString() + ", " + "(" + "$T" + ") "
                                + "extras.get(\"" + key + "\")); \n", backdoorClassName, ClassName.get(element.asType()));
                    } else {
                        injectMethodCodeBuilder.add(valueName + "." + parentElement.toString() + "." + element.toString());
                        injectMethodCodeBuilder.add(" = (" + "$T" + ") "
                                + "extras.get(\"" + key + "\"); \n", ClassName.get(element.asType()));

                    }
                } else {
                    injectMethodCodeBuilder.add(valueName + "." + element.toString());
                    injectMethodCodeBuilder.add(" = (" + "$T" + ") "
                            + "extras.get(\"" + key + "\"); \n", ClassName.get(element.asType()));
                }
                injectMethodCodeBuilder.endControlFlow();
            }
        }
    }

    private enum TypeEnum {
        ACTIVITY(sClassActivity, "activity"),
        FRAGMENT(sClassFragment, "fragment"),
        SUPPORT_FRAGMENT(sClassSupportFragment, "fragment"),
        SERVICE(sClassService, "service");

        ClassName className;
        String shortName;

        TypeEnum(ClassName className, String shortName) {
            this.className = className;
            this.shortName = shortName;
        }

        public ClassName getClassName() {
            return className;
        }

        public String getShortName() {
            return shortName;
        }
    }

}

