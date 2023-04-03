package ru.yojo.codegen.constants;

import java.util.List;
import java.util.Map;

import static java.lang.System.lineSeparator;

public enum ConstantsEnum {

    TABULATION("    "),

    /**
     * Attributes Yaml
     */
    PROPERTIES("properties"),
    TYPE("type"),
    FORMAT("format"),
    EXAMPLE("example"),
    DESCRIPTION("description"),
    REFERENCE("$ref"),
    REQUIRED("required"),
    MAX_LENGTH("maxLength"),
    MIN_LENGTH("minLength"),
    ITEMS("items"),
    PATTERN("pattern"),
    ENUMERATION("enum"),
    ARRAY("array"),
    OBJECT("object"),
    NAME("name"),
    TITLE("title"),
    SUMMARY("summary"),
    PAYLOAD("payload"),

    /**
     * Annotations
     */
    NOT_EMPTY_ANNOTATION("@NotEmpty"),
    NOT_NULL_ANNOTATION("@NotNull"),
    NOT_BLANK_ANNOTATION("@NotBlank"),
    SIZE_ANNOTATION("@Size"),
    SIZE_MIN_MAX_ANNOTATION("@Size(min = %s, max = %s)"),
    SIZE_MAX_ANNOTATION("@Size(max = %s)"),
    SIZE_MIN_ANNOTATION("@Size(min = %s)"),
    PATTERN_ANNOTATION("@Pattern(regexp = \"%s\")"),
    PATTERN_ANNOTATION_WITHOUT_REGEXP("@Pattern"),
    VALID_ANNOTATION("@Valid"),

    /**
     * Lombok annotations
     */
    LOMBOK_ALL_ARGS_CONSTRUCTOR_ANNOTATION("@AllArgsConstructor"),
    LOMBOK_NO_ARGS_CONSTRUCTOR_ANNOTATION("@NoArgsConstructor"),
    LOMBOK_DATA_ANNOTATION("@Data"),
    LOMBOK_ACCESSORS_ANNOTATION("@Accessors(fluent = true, chain = true)"),

    /**
     * Lombok imports
     */
    LOMBOK_ALL_ARGS_CONSTRUCTOR_IMPORT("lombok.AllArgsConstructor;"),
    LOMBOK_NO_ARGS_CONSTRUCTOR_IMPORT("lombok.NoArgsConstructor;"),
    LOMBOK_DATA_IMPORT("lombok.Data;"),
    LOMBOK_ACCESSORS_IMPORT("lombok.experimental.Accessors;"),

    /**
     * Other
     */
    IMPORT("import "),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime"),
    LONG("Long"),
    UUID("UUID"),
    BIG_DECIMAL("BigDecimal"),

    BIG_DECIMAL_IMPORT("java.math.BigDecimal;"),
    LOCAL_DATE_IMPORT("java.time.LocalDate;"),
    LOCAL_DATE_TIME_IMPORT("java.time.LocalDateTime;"),
    UUID_IMPORT("java.util.UUID;"),
    NOT_BLANK_IMPORT("javax.validation.constraints.NotBlank;"),
    NOT_EMPTY_IMPORT("javax.validation.constraints.NotEmpty;"),
    NOT_NULL_IMPORT("javax.validation.constraints.NotNull;"),
    SIZE_IMPORT("javax.validation.constraints.Size;"),
    PATTERN_IMPORT("javax.validation.constraints.Pattern;"),
    VALID_IMPORT("javax.validation.Valid;"),
    LIST_IMPORT("java.util.List;"),

    ARRAY_LIST("    private List<%s> %s;"),
    FIELD("    private %s %s;"),
    LIST_TYPE("List<%s>"),

    JAVA_DOC_START("    /**"),
    JAVA_DOC_END("     */"),
    JAVA_DOC_LINE("     * %s"),
    JAVA_DOC_EXAMPLE("     * Example: %s"),
    JAVA_DOC_CLASS_START("/**"),
    JAVA_DOC_CLASS_END("*/"),
    JAVA_DOC_CLASS_LINE("* %s"),

    GETTER("    public %s get%s() {" +
            lineSeparator() +
            "        return %s;" +
            lineSeparator() + "    }"),
    SETTER("    public void set%s(%s %s) {" +
            lineSeparator() +
            "        this.%s = %s;" +
            lineSeparator() + "    }"),
    DELIMETER("/"),
    MESSAGE_PACKAGE_IMPORT("messages;"),
    COMMON_PACKAGE_IMPORT("common;");

    public String getValue() {
        return value;
    }

    ConstantsEnum(String value) {
        this.value = value;
    }

    public static final Map<String, String> JAVA_TYPES_REQUIRED_ANNOTATIONS = Map.of(
            "String", NOT_BLANK_ANNOTATION.getValue(),
            "Integer", NOT_NULL_ANNOTATION.getValue(),
            "Long", NOT_NULL_ANNOTATION.getValue(),
            "Boolean", NOT_NULL_ANNOTATION.getValue(),
            "LocalDate", NOT_NULL_ANNOTATION.getValue(),
            "LocalDateTime", NOT_NULL_ANNOTATION.getValue(),
            "BigDecimal", NOT_NULL_ANNOTATION.getValue(),
            "Object", NOT_NULL_ANNOTATION.getValue());

    public static final Map<String, String> JAVA_TYPES_REQUIRED_IMPORTS = Map.of(
            NOT_BLANK_ANNOTATION.getValue(), NOT_BLANK_IMPORT.getValue(),
            NOT_EMPTY_ANNOTATION.getValue(), NOT_EMPTY_IMPORT.getValue(),
            NOT_NULL_ANNOTATION.getValue(), NOT_NULL_IMPORT.getValue(),
            SIZE_ANNOTATION.getValue(), SIZE_IMPORT.getValue(),
            PATTERN_ANNOTATION_WITHOUT_REGEXP.getValue(), PATTERN_IMPORT.getValue(),
            VALID_ANNOTATION.getValue(), VALID_IMPORT.getValue());

    public static final List<String> JAVA_DEFAULT_TYPES = List.of(
            "String", "Integer", "Long", "Boolean", "BigDecimal", "LocalDate", "UUID", "LocalDateTime"
    );
    private final String value;

    public static String formatString(ConstantsEnum constantsEnum, Object... strings) {
        return String.format(constantsEnum.getValue(), strings);
    }
}
