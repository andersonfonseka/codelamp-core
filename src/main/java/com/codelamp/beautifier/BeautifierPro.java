package com.codelamp.beautifier;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Beautifier PRO 100% compatível com qualquer JDK.
 * Não usa bibliotecas externas e é seguro para templates gerados pela CodeLamp.
 */
public class BeautifierPro {

    private static final String INDENT = "    "; // 4 espaços

    /**
     * Aplica todas as melhorias de formatação.
     */
    public static String beautify(String source) {

        if (source == null || source.isBlank()) {
            return source;
        }

        // Normaliza quebras de linha e tabs
        source = source.replace("\r", "").replace("\t", "    ");

        // 1) Limpar e organizar imports
        source = organizeImports(source);

        // 2) Normalização de indentação por blocos
        source = indentBraces(source);

        // 3) Inserir linha em branco entre métodos
        source = spaceBetweenMethods(source);

        // 4) Remover linhas vazias duplicadas
        source = removeDuplicateBlankLines(source);

        return source.trim() + "\n";
    }

    /**
     * Organiza imports:
     * - remove duplicados
     * - remove imports não usados
     * - ordena alfabeticamente
     */
    private static String organizeImports(String source) {

        String[] lines = source.split("\n");
        List<String> imports = new ArrayList<>();
        Set<String> unique = new HashSet<>();

        StringBuilder beforeImports = new StringBuilder();
        StringBuilder afterImports = new StringBuilder();

        boolean inImports = false;
        boolean passedImports = false;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("import ")) {
                inImports = true;

                if (!unique.contains(trimmed)) {
                    unique.add(trimmed);
                    imports.add(trimmed);
                }

            } else if (inImports && trimmed.isEmpty()) {
                // ignorar
            } else if (inImports) {
                // acabou sessão de imports
                passedImports = true;
                inImports = false;
                afterImports.append(line).append("\n");
            } else if (passedImports) {
                afterImports.append(line).append("\n");
            } else {
                beforeImports.append(line).append("\n");
            }
        }

        // Ordenar imports
        Collections.sort(imports);

        // Remover imports não utilizados
        String after = afterImports.toString();
        List<String> filtered = new ArrayList<>();

        for (String imp : imports) {
            String className = imp.substring("import ".length(), imp.length() - 1);
            String simple = className.substring(className.lastIndexOf('.') + 1);
            if (after.contains(simple)) {
                filtered.add(imp);
            }
        }

        StringBuilder rebuilt = new StringBuilder();
        rebuilt.append(beforeImports);

        if (!filtered.isEmpty()) {
            for (String imp : filtered) {
                rebuilt.append(imp).append("\n");
            }
            rebuilt.append("\n");
        }

        rebuilt.append(afterImports);
        return rebuilt.toString();
    }

    /**
     * Indenta código baseado em chaves { }.
     */
    private static String indentBraces(String source) {

        String[] lines = source.split("\n");
        List<String> result = new ArrayList<>();

        int level = 0;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.startsWith("}")) {
                level = Math.max(0, level - 1);
            }

            result.add(INDENT.repeat(level) + trimmed);

            if (trimmed.endsWith("{")) {
                level++;
            }
        }

        return String.join("\n", result);
    }

    /**
     * Insere linha em branco entre métodos para melhor organização visual.
     */
    private static String spaceBetweenMethods(String source) {

        List<String> lines = new ArrayList<>(Arrays.asList(source.split("\n")));
        List<String> output = new ArrayList<>();

        Pattern methodPattern = Pattern.compile(
                "^(public|private|protected)\\s+[^=]+\\s+[a-zA-Z0-9_]+\\s*\\("
        );

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i).trim();
            output.add(lines.get(i));

            if (i < lines.size() - 1) {

                Matcher curr = methodPattern.matcher(line);
                Matcher next = methodPattern.matcher(lines.get(i + 1).trim());

                if (curr.find() && !lines.get(i + 1).trim().isEmpty()) {
                    output.add("");
                }
                if (next.find() && !line.isEmpty()) {
                    output.add("");
                }
            }
        }

        return String.join("\n", output);
    }

    /**
     * Remove linhas vazias duplicadas.
     */
    private static String removeDuplicateBlankLines(String source) {

        String[] lines = source.split("\n");
        List<String> result = new ArrayList<>();

        boolean lastBlank = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                if (!lastBlank) {
                    result.add("");
                }
                lastBlank = true;
            } else {
                lastBlank = false;
                result.add(line);
            }
        }

        return String.join("\n", result);
    }
}
