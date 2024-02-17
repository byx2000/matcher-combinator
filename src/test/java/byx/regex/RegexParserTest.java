package byx.regex;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexParserTest {
    @Test
    public void test1() {
        Regex r = RegexParser.parse("a");
        assertTrue(r.match("a"));
        assertFalse(r.match(""));
        assertFalse(r.match("b"));
        assertFalse(r.match("aa"));

        r = RegexParser.parse("(a)");
        assertTrue(r.match("a"));
        assertFalse(r.match(""));
        assertFalse(r.match("b"));
        assertFalse(r.match("aa"));
    }

    @Test
    public void test2() {
        Regex r = RegexParser.parse(".");
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertTrue(r.match(" "));
        assertFalse(r.match(""));
    }

    @Test
    public void test3() {
        Regex r = RegexParser.parse("a*");
        assertTrue(r.match(""));
        assertTrue(r.match("a"));
        assertTrue(r.match("aaaaa"));
        assertFalse(r.match("aaab"));
        assertFalse(r.match("bbbbb"));
    }

    @Test
    public void test4() {
        Regex r = RegexParser.parse("a+");
        assertTrue(r.match("a"));
        assertTrue(r.match("aaaaa"));
        assertFalse(r.match(""));
    }

    @Test
    public void test5() {
        Regex r = RegexParser.parse("ab");
        assertTrue(r.match("ab"));
        assertFalse(r.match(""));
        assertFalse(r.match("a"));
        assertFalse(r.match("b"));
        assertFalse(r.match("xy"));
        assertFalse(r.match("abc"));
    }

    @Test
    public void test6() {
        Regex r = RegexParser.parse("apple");
        assertTrue(r.match("apple"));
        assertFalse(r.match(""));
        assertFalse(r.match("a"));
        assertFalse(r.match("app"));
        assertFalse(r.match("applex"));
        assertFalse(r.match("xyz"));
        assertFalse(r.match("sdjgfshgffgj"));
    }

    @Test
    public void test7() {
        Regex r = RegexParser.parse("a|b");
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertFalse(r.match(""));
        assertFalse(r.match("x"));
        assertFalse(r.match("ab"));
        assertFalse(r.match("xyz"));
    }

    @Test
    public void test8() {
        Regex r = RegexParser.parse("a|b|cd|e");
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertTrue(r.match("cd"));
        assertTrue(r.match("e"));
        assertFalse(r.match(""));
        assertFalse(r.match("x"));
        assertFalse(r.match("ab"));
        assertFalse(r.match("c"));
        assertFalse(r.match("bcd"));
        assertFalse(r.match("abcd"));
        assertFalse(r.match("xyz"));
    }

    @Test
    public void test9() {
        Regex r = RegexParser.parse("(a|b)*");
        assertTrue(r.match(""));
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertTrue(r.match("aa"));
        assertTrue(r.match("ab"));
        assertTrue(r.match("ba"));
        assertTrue(r.match("bb"));
        assertTrue(r.match("aabbabaabababaabababa"));
        assertTrue(r.match("babbabaabababaabababa"));
        assertTrue(r.match("aaaaaaaaaaa"));
        assertTrue(r.match("bbbbbbbbbbb"));
        assertFalse(r.match("c"));
        assertFalse(r.match("xyz"));
        assertFalse(r.match("aabbabaxabababaabababa"));
    }

    @Test
    public void test10() {
        Regex r = RegexParser.parse("(a|b)+");
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertTrue(r.match("aa"));
        assertTrue(r.match("ab"));
        assertTrue(r.match("ba"));
        assertTrue(r.match("bb"));
        assertTrue(r.match("aabbabaabababaabababa"));
        assertTrue(r.match("babbabaabababaabababa"));
        assertTrue(r.match("aaaaaaaaaaa"));
        assertTrue(r.match("bbbbbbbbbbb"));
        assertFalse(r.match(""));
        assertFalse(r.match("c"));
        assertFalse(r.match("xyz"));
        assertFalse(r.match("aabbabaxabababaabababa"));
    }

    @Test
    public void test11() {
        Regex r = RegexParser.parse("(ab)*");
        assertTrue(r.match(""));
        assertTrue(r.match("ab"));
        assertTrue(r.match("ababababababababab"));
        assertFalse(r.match("a"));
        assertFalse(r.match("b"));
        assertFalse(r.match("abba"));
        assertFalse(r.match("aba"));
        assertFalse(r.match("ababababababababa"));
    }

    @Test
    public void test12() {
        Regex r = RegexParser.parse("(a|ab)c");
        assertTrue(r.match("abc"));
        assertTrue(r.match("ac"));
        assertFalse(r.match("ab"));
        assertFalse(r.match("abx"));
        assertFalse(r.match("xc"));
        assertFalse(r.match("a"));
    }

    @Test
    public void test13() {
        Regex r = RegexParser.parse("(a*)*");
        assertTrue(r.match(""));
        assertTrue(r.match("a"));
        assertTrue(r.match("aaaaaaaaaaaaa"));
        assertFalse(r.match("b"));
        assertFalse(r.match("aaab"));
        assertFalse(r.match("bbbbbbbbbbbbb"));
    }

    @Test
    public void test14() {
        Regex r = RegexParser.parse("(a*)+");
        assertTrue(r.match(""));
        assertTrue(r.match("a"));
        assertTrue(r.match("aaaaaaaaaaaaa"));
        assertFalse(r.match("b"));
        assertFalse(r.match("aaab"));
        assertFalse(r.match("bbbbbbbbbbbbb"));
    }

    @Test
    public void test15() {
        Regex r = RegexParser.parse("(0|1(01*0)*1)*");
        assertTrue(r.match("0"));
        assertFalse(r.match("1"));
        assertFalse(r.match("10"));
        assertTrue(r.match("11"));
        assertFalse(r.match("100"));
        assertFalse(r.match("101"));
        assertTrue(r.match("110"));
        assertFalse(r.match("111"));
        assertFalse(r.match("1000"));
        assertTrue(r.match("1000001001001111010"));
        assertFalse(r.match("1000001001001111011"));
        assertFalse(r.match("1000001001001111100"));
        assertTrue(r.match("1000001001001111101"));
        assertTrue(r.match("111010010101011001000001110011010111101110101111101110110"));
        assertFalse(r.match("111010010101011001000001110011010111101110101111101110111"));
        assertFalse(r.match("111010010101011001000001110011010111101110101111101111000"));
        assertTrue(r.match("111010010101011001000001110011010111101110101111101111001"));
    }

    @Test
    public void test16() {
        Regex r = RegexParser.parse("a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertTrue(r.match("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(r.match("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void test17() {
        Regex r = RegexParser.parse("(((a*)*)*)*");
        assertTrue(r.match("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(r.match("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"));
        assertFalse(r.match("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void test18() {
        Regex r = RegexParser.parse("X(.+)+X");
        assertTrue(r.match("X==============================================X"));
        assertFalse(r.match("==XX=============================================="));
    }

    @Test
    public void test19() {
        Regex r = RegexParser.parse("((0|1)+)+b");
        assertFalse(r.match("10101010110101001100101010101010101010101010101010000"));
        assertTrue(r.match("10101010110101001100101010101010101010101010101010000b"));
    }

    @Test
    public void test20() {
        Regex r = RegexParser.parse("(.*)adidas(.*)");
        assertTrue(r.match("adidas"));
        assertTrue(r.match("adidasxxxxxxx"));
        assertTrue(r.match("yyyyyyyadidas"));
        assertTrue(r.match("asfdjaasadaadidasidgfsaaaasdfkjsdskdf"));
        assertFalse(r.match("adida"));
        assertFalse(r.match("adixdas"));
        assertFalse(r.match("didas"));
        assertFalse(r.match("sadgvajsvfjkdbfjcsadidaakjgkahfdjksdbfjks"));
    }

    @Test
    public void test21() {
        Regex r = RegexParser.parse("[0-9]");
        assertTrue(r.match("0"));
        assertTrue(r.match("5"));
        assertTrue(r.match("9"));
        assertFalse(r.match(""));
        assertFalse(r.match("a"));
    }

    @Test
    public void test22() {
        Regex r = RegexParser.parse("[abc]");
        assertTrue(r.match("a"));
        assertTrue(r.match("b"));
        assertTrue(r.match("c"));
        assertFalse(r.match(""));
        assertFalse(r.match("x"));
    }

    @Test
    public void test23() {
        Regex r = RegexParser.parse("[a-zA-Z]");
        assertTrue(r.match("a"));
        assertTrue(r.match("x"));
        assertTrue(r.match("z"));
        assertTrue(r.match("A"));
        assertTrue(r.match("X"));
        assertTrue(r.match("Z"));
        assertFalse(r.match(""));
        assertFalse(r.match("5"));
        assertFalse(r.match("?"));
    }

    @Test
    public void test24() {
        Regex r = RegexParser.parse("[_a-zA-Z][_0-9a-zA-Z]*");
        assertTrue(r.match("_"));
        assertTrue(r.match("_var"));
        assertTrue(r.match("count"));
        assertTrue(r.match("Add"));
        assertTrue(r.match("_____"));
        assertTrue(r.match("push123"));
        assertTrue(r.match("abc123xXYy"));
        assertFalse(r.match(""));
        assertFalse(r.match("123abc"));
        assertFalse(r.match("dfg3fd fsgbsdf23"));
    }

    @Test
    public void test25() {
        // 匹配3的倍数
        Regex r = RegexParser.parse("[0369]*(([147][0369]*|[258][0369]*[258][0369]*)([147][0369]*[258][0369]*)*([258][0369]*|[147][0369]*[147][0369]*)|[258][0369]*[147][0369]*)*");

        for (int i = 0; i <= 100000; ++i) {
            if (i % 3 == 0) {
                assertTrue(r.match(String.valueOf(i)));
            } else {
                assertFalse(r.match(String.valueOf(i)));
            }
        }

        assertTrue(r.match("1306037620370620974"));
        assertFalse(r.match("1306037620370620975"));
        assertFalse(r.match("1306037620370620976"));
        assertTrue(r.match("1306037620370620977"));
    }

    @Test
    public void test26() {
        Regex r = RegexParser.parse("\\.");
        assertTrue(r.match("."));
        assertFalse(r.match(""));
        assertFalse(r.match("a"));

        r = RegexParser.parse("\\\\");
        assertTrue(r.match("\\"));
        assertFalse(r.match(""));
        assertFalse(r.match("a"));
    }

    @Test
    public void test27() {
        Regex r = RegexParser.parse("\\**");
        assertTrue(r.match(""));
        assertTrue(r.match("*"));
        assertTrue(r.match("*****"));

        r = RegexParser.parse("(\\(\\)|\\[\\])+");
        assertTrue(r.match("()"));
        assertTrue(r.match("[]"));
        assertTrue(r.match("()[][]()()[]"));
        assertTrue(r.match("[][]()()()[][]()"));
        assertFalse(r.match(""));
        assertFalse(r.match("(())"));
        assertFalse(r.match("[)"));
    }

    @Test
    public void test28() {
        Regex r = RegexParser.parse("(a*)*");
        assertTrue(r.match("a".repeat(1000)));
        assertFalse(r.match("a".repeat(1000) + "b"));
    }

    @Test
    public void testFileCases() {
        // 测试数据来源：https://loj.ac/p/118
        for (int i = 1; i <= 11; ++i) {
            String inputFile = "regular" + i + ".in";
            String outputFile = "./regular" + i + ".out";

            Scanner scanner1 = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(inputFile)));
            Scanner scanner2 = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(outputFile)));
            while (scanner1.hasNext()) {
                String expr = scanner1.nextLine();
                System.out.println("expr: " + expr);
                String str = scanner1.nextLine();
                System.out.println("str: " + str);
                String ans = scanner2.nextLine();
                System.out.println("ans: " + ans);
                System.out.println("==============================");

                Regex r = RegexParser.parse(expr);
                if ("Yes".equals(ans)) {
                    assertTrue(r.match(str));
                } else {
                    assertFalse(r.match(str));
                }
            }
        }
    }
}
