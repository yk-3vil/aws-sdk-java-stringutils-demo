package com.example;

import com.amazonaws.util.StringUtils;

/**
 * AWS SDK for Java v1 — StringUtils.isNullOrEmpty() の動作確認デモ
 *
 * <p>AWS SDK for Java v1 が提供する {@link com.amazonaws.util.StringUtils#isNullOrEmpty(String)} は、
 * 文字列が null または空文字列("") の場合に true を返すユーティリティメソッドである。</p>
 *
 * <p><b>注意:</b> このメソッドは空白文字のみの文字列(" " など)を空とは判定しない。
 * Apache Commons Lang の {@code StringUtils.isBlank()} とは挙動が異なる点に留意すること。</p>
 *
 * <p>期待される実行結果:</p>
 * <pre>
 * StringUtils.isNullOrEmpty(null      ) = true
 * StringUtils.isNullOrEmpty(""        ) = true
 * StringUtils.isNullOrEmpty(" "       ) = false
 * StringUtils.isNullOrEmpty("bob"     ) = false
 * StringUtils.isNullOrEmpty("  bob  " ) = false
 * </pre>
 */
public class AwsSdkStringUtilsDemoV1 {

    // === チェック対象の定数定義 ===

    /** null — isNullOrEmpty() が true を返すケース */
    private static final String NULL_VALUE = null;

    /** 空文字列 — isNullOrEmpty() が true を返すケース */
    private static final String EMPTY = "";

    /** 半角スペースのみ — isNullOrEmpty() は false を返す（空白チェックではない） */
    private static final String SINGLE_SPACE = " ";

    /** 通常の文字列 — isNullOrEmpty() は false を返す */
    private static final String BOB = "bob";

    /** 前後に空白を含む文字列 — isNullOrEmpty() は false を返す */
    private static final String BOB_WITH_SPACES = "  bob  ";

    public static void main(String[] args) {
        System.out.println("=== AWS SDK for Java v1 - StringUtils.isNullOrEmpty Demo ===");
        System.out.println();

        // null と空文字列 → true
        check("null", NULL_VALUE);
        check("\"\"", EMPTY);

        // 空白のみ・通常文字列・前後空白付き → false
        check("\" \"", SINGLE_SPACE);
        check("\"bob\"", BOB);
        check("\"  bob  \"", BOB_WITH_SPACES);
    }

    /**
     * isNullOrEmpty() の判定結果を表示するヘルパーメソッド
     *
     * @param label  表示用ラベル（どの値をテストしているかを示す）
     * @param value  判定対象の文字列
     */
    private static void check(String label, String value) {
        boolean result = StringUtils.isNullOrEmpty(value);
        System.out.printf("StringUtils.isNullOrEmpty(%-10s) = %s%n", label, result);
    }
}
