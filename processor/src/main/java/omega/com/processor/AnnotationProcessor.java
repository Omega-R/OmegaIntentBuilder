package omega.com.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
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
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import omega.com.annotations.OmegaActivity;
import omega.com.annotations.OmegaExtra;

import static omega.com.processor.StringUtils.formatMethodName;
import static omega.com.processor.StringUtils.isSuitableName;
import static omega.com.processor.StringUtils.replaceFirstToLowerCase;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"omega.com.annotations.OmegaActivity", "omega.com.annotations.OmegaExtra"})
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
    private static final ClassName sAppOmegaIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_OMEGA_INTENT_BUILDER);
    private static final ClassName sAppActivityIntentBuilderClass = ClassName.get(PACKAGE_NAME, APP_ACTIVITY_INTENT_BUILDER);

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
            List<MethodSpec> list = generateActivityBuilderMethods(mRoundEnvironment.getElementsAnnotatedWith(OmegaActivity.class));

            TypeSpec activityIntentBuilder = TypeSpec.classBuilder(APP_ACTIVITY_INTENT_BUILDER)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(sClassBaseBuilder)
                    .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                    .addMethod(generateClassConstructor(true).build())
                    .addMethod(MethodSpec.methodBuilder("createIntent")
                            .returns(sClassIntent)
                            .addStatement("return new $T()", sClassIntent)
                            .addModifiers(Modifier.PUBLIC)
                            .build())
                    .addMethods(list)
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
                .addMethod(generateClassConstructor(true).build())
                .addField(sClassContext, "context", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.methodBuilder("from")
                        .returns(sAppOmegaIntentBuilderClass)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(sClassContext, "context")
                        .addStatement("return new $T(context)", sAppOmegaIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("appActivity")
                        .returns(sAppActivityIntentBuilderClass)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return new $T(context)", sAppActivityIntentBuilderClass)
                        .build())
                .addMethod(MethodSpec.methodBuilder("inject")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ParameterSpec.builder(sClassActivity, "activity").build())
                        .addStatement("AppActivityIntentBuilder.inject(activity)", sAppActivityIntentBuilderClass)
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
                .addMethod(generateClassConstructor(false)
                        .addStatement("intent = new Intent(context, $T.class)", activityClassName)
                        .build())
                .addMethod(generateCreateIntentMethod(activityClassName))
                .addMethods(generateExtraMethods(element, ClassName.get(packageName, className)))
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

    private MethodSpec.Builder generateClassConstructor(boolean withContextField) {
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

    private List<MethodSpec> generateExtraMethods(Element parentElement, ClassName returnClassname) {
        List<MethodSpec> methodSpecList = new ArrayList<>();
        List<? extends Element> subList = parentElement.getEnclosedElements();

        if (subList == null || subList.isEmpty()) {
            return methodSpecList;
        }

        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ParameterSpec.builder(ClassName.get(parentElement.asType()), "activity").build())
                .addStatement("$T extras = activity.getIntent().getExtras()", sClassBundle);

        CodeBlock.Builder injectCodeBuilder = CodeBlock.builder();
        injectCodeBuilder.beginControlFlow("if(extras != null) ");

        for (Element subElement : subList) {
            OmegaExtra annotation = subElement.getAnnotation(OmegaExtra.class);
            if (annotation != null) {
                String methodName = formatMethodName(annotation);
                String key = annotation.value();

                if (!isSuitableName(methodName)) {
                    methodName = replaceFirstToLowerCase(subElement.toString());
                }
                if (StringUtils.isEmpty(key)) {
                    key = subElement.toString();
                }

                ParameterSpec parameter = ParameterSpec.builder(ClassName.get(subElement.asType()), "value").build();

                methodSpecList.add(MethodSpec.methodBuilder(methodName)
                        .returns(returnClassname)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(parameter)
                        .addStatement("intent.putExtra(\"" + key + "\", value)")
                        .addStatement("return this", returnClassname)
                        .build());

                if (subElement.getModifiers().contains(Modifier.PRIVATE)) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR, subElement.getSimpleName() + " in " + parentElement.getSimpleName() + " can't be private;");
                } else {

                    injectCodeBuilder.beginControlFlow("if(extras.containsKey(\"" + key + "\"))")
                            .add("activity." + subElement.toString()
                                    + " = (" + "$T" + ") "
                                    + "extras.get(\"" + key + "\"); \n", ClassName.get(subElement.asType()))
                            .endControlFlow();
                }
            }
        }

        injectCodeBuilder.endControlFlow();
        injectMethodBuilder.addCode(injectCodeBuilder.build());
        methodSpecList.add(injectMethodBuilder.build());
        return methodSpecList;
    }

}

