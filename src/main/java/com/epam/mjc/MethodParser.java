package com.epam.mjc;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    public static final List<String> ACCESS_MODIFIERS = List.of("public", "protected", "private");

    public MethodSignature parseFunction(String signatureString) {

        String accessModifier = null;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        // signature and parameters preparing (tokenizing):
        List<String> signNParam = MethodParserTokenizer(signatureString, "()");
        List<String> sign = MethodParserTokenizer(signNParam.get(0), " ");
        if (signNParam.size() > 1) {
            List<String> param = MethodParserTokenizer(signNParam.get(1), " ");
            List<String> params = MethodParserTokenizer(signNParam.get(1), ",, ");
            for (int i = 0; i < params.size(); i+=2) {
                System.out.print("type -> " + params.get(i));
                System.out.println(" name -> " + params.get(i+1));
                MethodSignature.Argument arg = new MethodSignature.Argument(params.get(i), params.get(i+1));
                arguments.add(arg);
            }
        }

        // check optional access modifiers:
        if (ACCESS_MODIFIERS.contains(sign.get(0))) {
            accessModifier = sign.get(0);
            sign.remove(0);
        }
        // after deleting access modifiers unification ret type and name setting:
        returnType = sign.get(0);
        methodName = sign.get(1);

        MethodSignature ms = new MethodSignature(methodName, arguments);
        ms.setReturnType(returnType);
        ms.setAccessModifier(accessModifier);

        return ms;
    }

    public List<String> MethodParserTokenizer(String stringToParse, String delimiter) {
        // signature and parameters preparing (tokenizing)
        List<String> elements = new ArrayList<>();
        StringTokenizer signMParamTokenizer = new StringTokenizer(stringToParse, delimiter);
        while (signMParamTokenizer.hasMoreTokens()) {
            elements.add(signMParamTokenizer.nextToken());
        }
        return elements;
    }

}
