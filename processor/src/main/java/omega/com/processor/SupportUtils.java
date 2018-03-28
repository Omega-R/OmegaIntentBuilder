package omega.com.processor;

import com.squareup.javapoet.ClassName;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

class SupportUtils {

    private static final ClassName sClassObject = ClassName.get("java.lang", "Object");

    static boolean isDefaultOrProtected(Element element) {
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(Modifier.PROTECTED)) return true;
        if (modifiers.contains(Modifier.PUBLIC)) return false;
        if (modifiers.contains(Modifier.PRIVATE)) return false;
        return true;
    }

    static boolean isInstanceOfSuperclass(Element element, ClassName...superClassNames) {
        for (ClassName className : superClassNames) {
            if (isInstanceOfSuperclass(element, className)) return true;
        }
        return false;
    }

    static boolean isInstanceOfSuperclass(Element element, ClassName superClassName) {
        TypeMirror typeMirror = element.asType();
        TypeElement typeElement = (TypeElement)((DeclaredType)typeMirror).asElement();
        TypeMirror superclass = typeElement.getSuperclass();

        if (superclass.toString().equalsIgnoreCase(superClassName.toString())) {
            return true;
        }
        while (!superclass.toString().equalsIgnoreCase(sClassObject.toString())) {
            typeMirror = superclass;
            typeElement = (TypeElement)((DeclaredType)typeMirror).asElement();
            superclass = typeElement.getSuperclass();

            if (superclass.toString().equalsIgnoreCase(superClassName.toString())) {
                return true;
            }
        }

        return false;
    }


}
