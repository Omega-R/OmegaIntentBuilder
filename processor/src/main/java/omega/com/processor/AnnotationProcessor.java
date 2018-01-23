package omega.com.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
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

import static omega.com.processor.StringUtils.formatMethodName;
import static omega.com.processor.StringUtils.isEmpty;
import static omega.com.processor.StringUtils.isSuitableName;
import static omega.com.processor.StringUtils.replaceFirstToLowerCase;
import static omega.com.processor.StringUtils.replaceFirstToUpperCase;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"omega.com.annotations.OmegaActivity",
                           "omega.com.annotations.OmegaExtra",
                           "omega.com.annotations.OmegaExtraModel"})
public class AnnotationProcessor extends AbstractProcessor {

    private static final ClassName sClassIntent = ClassName.get("android.content", "Intent");
    private static final ClassName sClassContext = ClassName.get("android.content", "Context");
    private static final ClassName sClassActivity = ClassName.get("android.app", "Activity");
    private static final ClassName sClassBundle = ClassName.get("android.os", "Bundle");

    private static final ClassName sClassBaseBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder.builders", "BaseBuilder");
    private static final ClassName sClassOmegaIntentBuilder = ClassName.get("com.omega_r.libs.omegaintentbuilder", "OmegaIntentBuilder");

    private static final String PACKAGE_NAME = "com.omega_r.libs.omegaintentbuilder";
    private static final String APP_ACTIVITY_INTENT_BUILDER = "AppActivityIntentBuilder";
    private static final String APP_OMEGA_INTENT_BUILDER = "AppOmegaIntentBuilder";
    private static final ClassName sClassAppOmegaIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_OMEGA_INTENT_BUILDER);
    private static final ClassName sClassAppActivityIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_ACTIVITY_INTENT_BUILDER);

    private static final String OMEGA_GENERATED = "$OmegaGenerated";
    private static final String SET = "set";
    private static final String MODEL = "model";
    private static final String VALUE = "value";

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElements;
    private RoundEnvironment mRoundEnvironment;
    private Element element;

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
            List<MethodSpec> activityBuildersList = generateActivityBuilderMethods(mRoundEnvironment.getElementsAnnotatedWith(OmegaActivity.class));

            TypeSpec activityIntentBuilder = TypeSpec.classBuilder(APP_ACTIVITY_INTENT_BUILDER)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(sClassBaseBuilder)
                    .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                    .addMethod(generateClassConstructorMethod(true).build())
                    .addMethod(MethodSpec.methodBuilder("createIntent")
                            .returns(sClassIntent)
                            .addStatement("return new $T()", sClassIntent)
                            .addModifiers(Modifier.PUBLIC)
                            .build())
                    .addMethods(activityBuildersList)
                    .build();

            JavaFile.builder(PACKAGE_NAME, activityIntentBuilder)
                    .build()
                    .writeTo(mFiler);
            generateAppOmegaIntentBuilder();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void generateAppOmegaIntentBuilder() throws IOException {
        TypeSpec omegaIntentBuilder = TypeSpec.classBuilder(APP_OMEGA_INTENT_BUILDER)
                .addModifiers(Modifier.PUBLIC)
                .superclass(sClassOmegaIntentBuilder)
                .addMethod(generateClassConstructorMethod(true).build())
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
                .addMethod(MethodSpec.methodBuilder("inject")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ParameterSpec.builder(sClassActivity, "activity").build())
                        .addStatement("AppActivityIntentBuilder.inject(activity)", sClassAppActivityIntentBuilderClass)
                        .build())
                .build();

        JavaFile.builder(PACKAGE_NAME, omegaIntentBuilder)
                .build()
                .writeTo(mFiler);
    }

    private List<MethodSpec> generateActivityBuilderMethods(Set<? extends Element> elements) {
        List<Element> list = new ArrayList<>();
        List<ClassName> activityList = new ArrayList<>();

        for (Element element : elements) {
            if (generateActivityBuilderClass(element)) {
                list.add(element);
                ClassName activityClassName = ClassName.get(mElements.getPackageOf(element).getQualifiedName().toString(),
                        element.getSimpleName().toString());
                activityList.add(activityClassName);
            }
        }

        List<MethodSpec> methodList = new ArrayList<>();
        for (Element element : list) {
            methodList.add(generateReturnActivityBuilderMethod(element));
        }

        methodList.add(generateInjectMethod(activityList));
        return methodList;
    }

    private boolean generateActivityBuilderClass(Element element) {
        if (element.getKind() != ElementKind.CLASS) {
            mMessager.printMessage(Diagnostic.Kind.ERROR, "Annotation activity can only be used for classes!");
            return false;
        }

        String className = element.getSimpleName().toString() + "Builder";
        ClassName activityClassName = ClassName.get(mElements.getPackageOf(element).getQualifiedName().toString(),
                                                    element.getSimpleName().toString());
        String packageName = mElements.getPackageOf(element).toString();

        TypeSpec intentBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .superclass(sClassBaseBuilder)
                .addField(sClassIntent, "intent", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(generateClassConstructorMethod(false)
                        .addStatement("intent = new Intent(context, $T.class)", activityClassName)
                        .build())
                .addMethod(generateCreateIntentMethod(activityClassName))
                .addMethods(generateActivityBuilderMethods(element, ClassName.get(packageName, className)))
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

    private MethodSpec generateReturnActivityBuilderMethod(Element element) {
        String methodName = element.getSimpleName().toString();
        String returnClassName = methodName + "Builder";
        methodName = replaceFirstToLowerCase(methodName);
        String packageName = mElements.getPackageOf(element).toString();

        return MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addStatement("return new " + returnClassName + "(context)")
                .returns(ClassName.get(packageName, returnClassName))
                .build();
    }

    private MethodSpec generateInjectMethod(List<ClassName> list) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("inject")
                                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                                .addParameter(ParameterSpec.builder(sClassActivity, "activity").build());
        CodeBlock.Builder codeBuilder = CodeBlock.builder();
        for (ClassName clsName : list) {
            String activityName = clsName.simpleName();
            ClassName fullClassName = ClassName.get(clsName.packageName(), activityName);
            codeBuilder.beginControlFlow("if(activity instanceof $T)", fullClassName)
                    .addStatement(activityName + "Builder.inject((" + activityName + ") activity)", activityName)
                    .endControlFlow();
        }
        builder.addCode(codeBuilder.build());
        return builder.build();
    }

    private MethodSpec.Builder generateClassConstructorMethod(boolean withContextField) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(sClassContext, "context")
                .addStatement("super(context)");
        if (withContextField) {
            builder.addStatement("this.$N = $N", "context", "context");
        }
        return builder;
    }

    private MethodSpec generateCreateIntentMethod(ClassName activityClassName) {
        return MethodSpec.methodBuilder("createIntent")
                .returns(sClassIntent)
                .addStatement("return intent", activityClassName)
                .addModifiers(Modifier.PUBLIC)
                .build();
    }

    private List<MethodSpec> generateActivityBuilderMethods(Element parentElement, ClassName returnClassname) {
        List<MethodSpec> methodSpecList = new ArrayList<>();
        List<? extends Element> subList = parentElement.getEnclosedElements();

        if (subList == null || subList.isEmpty()) {
            return methodSpecList;
        }

        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ParameterSpec.builder(ClassName.get(parentElement.asType()), "activity").build())
                .addStatement("$T extras = activity.getIntent().getExtras()", sClassBundle);

        CodeBlock.Builder injectMethodCodeBuilder = CodeBlock.builder();
        injectMethodCodeBuilder.beginControlFlow("if(extras != null) ");

        for (Element subElement : subList) {
            generateExtraMethod(methodSpecList, injectMethodCodeBuilder, parentElement,
                    returnClassname, subElement,  "", false);

            OmegaExtraModel extraModelAnnotation = subElement.getAnnotation(OmegaExtraModel.class);
            if (extraModelAnnotation != null ) {
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
                        generateExtraMethod(methodSpecList, injectMethodCodeBuilder, subElement,
                                returnClassname, childElement, prefix, true);
                    }
                }
            }
        }

        injectMethodCodeBuilder.endControlFlow();
        injectMethodBuilder.addCode(injectMethodCodeBuilder.build());
        methodSpecList.add(injectMethodBuilder.build());
        return methodSpecList;
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

    private void generateExtraMethod(List<MethodSpec> methodSpecList,
                                     CodeBlock.Builder injectMethodCodeBuilder,
                                     Element parentElement,
                                     ClassName returnClassName,
                                     Element element,
                                     String methodNamePrefix,
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

            methodSpecList.add(MethodSpec.methodBuilder(methodName)
                    .returns(returnClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(parameter)
                    .addStatement("intent.putExtra(\"" + key + "\"," + VALUE + ")")
                    .addStatement("return this", returnClassName)
                    .build());

            if (element.getModifiers().contains(Modifier.PRIVATE)) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, element.getSimpleName() + " in " + parentElement.getSimpleName() + " can't be private;");
            } else {
                injectMethodCodeBuilder.beginControlFlow("if(extras.containsKey(\"" + key + "\"))");
                if(fromOmegaModel) {
                    if (isDefaultOrProtected(element)) {
                        Element omegaModelElement = ((DeclaredType) parentElement.asType()).asElement();
                        String shortClassName = omegaModelElement.getSimpleName().toString() + OMEGA_GENERATED;
                        String packageName = mElements.getPackageOf(omegaModelElement).toString();
                        ClassName backdoorClassName = ClassName.get(packageName, shortClassName);
                        String method = SET + replaceFirstToUpperCase(element.getSimpleName().toString());

                        injectMethodCodeBuilder.add( "$T." + method + "(activity."
                                + parentElement.toString() + ", " + "(" + "$T" + ") "
                                + "extras.get(\"" + key + "\")); \n", backdoorClassName, ClassName.get(element.asType()));
                    } else {
                        injectMethodCodeBuilder.add("activity." + parentElement.toString() + "." + element.toString());
                        injectMethodCodeBuilder.add(" = (" + "$T" + ") "
                                + "extras.get(\"" + key + "\"); \n", ClassName.get(element.asType()));

                    }
                } else {
                    injectMethodCodeBuilder.add("activity." + element.toString());
                    injectMethodCodeBuilder.add(" = (" + "$T" + ") "
                            + "extras.get(\"" + key + "\"); \n", ClassName.get(element.asType()));
                }
                injectMethodCodeBuilder.endControlFlow();
            }
        }
    }

    private boolean isDefaultOrProtected(Element element) {
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(Modifier.PROTECTED)) return true;
        if (modifiers.contains(Modifier.PUBLIC)) return false;
        if (modifiers.contains(Modifier.PRIVATE)) return false;
        return true;
    }

}

