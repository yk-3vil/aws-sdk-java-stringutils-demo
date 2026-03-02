package com.example;

import software.amazon.awssdk.utils.StringUtils;

/**
 * AWS SDK for Java v2 — StringUtils.isBlank() / isEmpty() の動作確認デモ
 *
 * <p>AWS SDK for Java v2 が提供する {@link software.amazon.awssdk.utils.StringUtils} には、
 * 文字列の空判定メソッドが2種類ある。</p>
 *
 * <ul>
 *   <li>{@code isEmpty(CharSequence)} — null または空文字列("") の場合に true を返す</li>
 *   <li>{@code isBlank(CharSequence)} — null、空文字列("")、または空白文字のみの場合に true を返す</li>
 * </ul>
 *
 * <p><b>v1 との違い:</b> v1 の {@code isNullOrEmpty()} は v2 の {@code isEmpty()} に相当する。
 * v2 では空白文字のみの判定に対応した {@code isBlank()} が追加されている。</p>
 *
 * <p>期待される実行結果:</p>
 * <pre>
 * --- isEmpty ---
 * StringUtils.isEmpty(null      ) = true
 * StringUtils.isEmpty(""        ) = true
 * StringUtils.isEmpty(" "       ) = false
 * StringUtils.isEmpty("bob"     ) = false
 * StringUtils.isEmpty("  bob  " ) = false
 *
 * --- isBlank ---
 * StringUtils.isBlank(null      ) = true
 * StringUtils.isBlank(""        ) = true
 * StringUtils.isBlank(" "       ) = true
 * StringUtils.isBlank("bob"     ) = false
 * StringUtils.isBlank("  bob  " ) = false
 * </pre>
 */
public class AwsSdkStringUtilsDemoV2 {

    // === チェック対象の定数定義 ===

    /** null — isEmpty(): true, isBlank(): true */
    private static final String NULL_VALUE = null;

    /** 空文字列 — isEmpty(): true, isBlank(): true */
    private static final String EMPTY = "";

    /** 半角スペースのみ — isEmpty(): false, isBlank(): true（両メソッドの差異が現れるケース） */
    private static final String SINGLE_SPACE = " ";

    /** 通常の文字列 — isEmpty(): false, isBlank(): false */
    private static final String BOB = "bob";

    /** 前後に空白を含む文字列 — isEmpty(): false, isBlank(): false */
    private static final String BOB_WITH_SPACES = "  bob  ";

    public static void main(String[] args) {
        System.out.println("=== AWS SDK for Java v2 - StringUtils.isEmpty / isBlank Demo ===");

        // --- isEmpty: null と空文字列のみ true ---
        System.out.println();
        System.out.println("--- isEmpty ---");

        // null と空文字列 → true
        checkEmpty("null", NULL_VALUE);
        checkEmpty("\"\"", EMPTY);

        // 空白のみ・通常文字列・前後空白付き → false
        checkEmpty("\" \"", SINGLE_SPACE);
        checkEmpty("\"bob\"", BOB);
        checkEmpty("\"  bob  \"", BOB_WITH_SPACES);

        // --- isBlank: null・空文字列・空白のみ が true ---
        System.out.println();
        System.out.println("--- isBlank ---");

        // null と空文字列 → true
        checkBlank("null", NULL_VALUE);
        checkBlank("\"\"", EMPTY);

        // 空白のみ → true（isEmpty との違い）
        checkBlank("\" \"", SINGLE_SPACE);

        // 通常文字列・前後空白付き → false
        checkBlank("\"bob\"", BOB);
        checkBlank("\"  bob  \"", BOB_WITH_SPACES);
    }

    /**
     * isEmpty() の判定結果を表示するヘルパーメソッド
     *
     * @param label  表示用ラベル（どの値をテストしているかを示す）
     * @param value  判定対象の文字列
     */
    private static void checkEmpty(String label, String value) {
        boolean result = StringUtils.isEmpty(value);
        System.out.printf("StringUtils.isEmpty(%-10s) = %s%n", label, result);
    }

    /**
     * isBlank() の判定結果を表示するヘルパーメソッド
     *
     * @param label  表示用ラベル（どの値をテストしているかを示す）
     * @param value  判定対象の文字列
     */
    private static void checkBlank(String label, String value) {
        boolean result = StringUtils.isBlank(value);
        System.out.printf("StringUtils.isBlank(%-10s) = %b%n", label, result);
    }
}
