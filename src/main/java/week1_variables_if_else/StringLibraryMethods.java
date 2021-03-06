package week1_variables_if_else;

import java.util.List;

public class StringLibraryMethods {

    public static void main(String[] args) {

        String quote = "Java is to JavaScript what Car is to Carpet.";  // Chris Heilmann, https://christianheilmann.com/

        //toUpperCase, and toLowerCase
        System.out.println(quote.toUpperCase());
        System.out.println(quote.toLowerCase());

        //replace - replaces one character with another
        String replaced = quote.replace(" ", "#");
        System.out.println(replaced);

        //length - what you'd expect
        System.out.println(quote.length());

        //indexOf - find position of a character in a string
        //remember count from 0
        System.out.println(quote.indexOf("J"));
        System.out.println(quote.indexOf("C"));

        //contains - does one string contain another?
        System.out.println(quote.contains("Java"));
        System.out.println(quote.contains("Ruby"));

        //equals and equalsIgnoreCase. Don't use == to test equality in Strings

        String string1 = "HELLO";
        String string2 = "Hello";
        System.out.println(string1.equals(string2));
        System.out.println(string1.equalsIgnoreCase(string2));

        //To join strings, you can use +

        String a = "The Complete Java";
        String b = "Reference";
        String textbook = a + " " + b;
        System.out.println(textbook);


        //startsWith and endsWith

        //Does this string end with a period?
        System.out.println(quote.endsWith("."));
        //Does this string start with C#?
        System.out.println(quote.startsWith("C#"));
        //Does this string start with Java?
        System.out.println(quote.startsWith("Java"));

        //Substring makes a new String by copying selected characters from a String
        //The arguments are the beginning character index and ending index
        //Omit the second index and the substring will assume the end is the end of the string
        String endOfString = quote.substring(27);
        System.out.println(endOfString);

        String javascript = quote.substring(11, 21);
        System.out.println(javascript);

        //trim removes whitespace - tabs, spaces, newlines etc. before and after a string
        //  remember \t and \n, the tab and newline characters
        String spacesAndTabsAndNewlines = "    \t \t I'm a string      \t \t \n \n";
        System.out.println(spacesAndTabsAndNewlines);
        String trimmed = spacesAndTabsAndNewlines.trim();
        System.out.println(trimmed);

        //Split up a string
        //Returns an array of strings. Arrays and loops next week.
        String[] words = quote.split(" ");
        for (String word : words) {
            System.out.println(word);
        }

        //Format strings

        String product = "Widgets";
        int quantity = 6;
        double price = 9.99;
        double totalSale = quantity * price;

        //Insert data into the string
        String formattedSalesInfo = String.format("A sale of %d %s at $%f each is a total of %f", quantity, product, price, totalSale);
        System.out.println(formattedSalesInfo);

        //Variation: display double with two decimal places

        String neatlyFormattedSalesInfo = String.format("A sale of %d %s at $%.2f each is a total of %.2f", quantity, product, price, totalSale);
        System.out.println(neatlyFormattedSalesInfo);

        // Join - join a iterable sequence into a string, joined by another string
        List<String> pieces = List.of("This", "is", "the", "end", "of", "the", "file");
        String sentence = String.join(" ", pieces);
        System.out.println(sentence);  // This is the end of the file

        sentence = String.join(" *-*-*-* ", pieces);
        System.out.println(sentence);  // This *-*-*-* is *-*-*-* the *-*-*-* end *-*-*-* of *-*-*-* the *-*-*-* file

    }


}
