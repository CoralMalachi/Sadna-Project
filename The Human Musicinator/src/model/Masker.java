package model;

import java.util.Random;

/**
 * This interface represents a masker interface.
 */
public interface Masker {

    /**
     * This method returns a masked string that corresponds the argument string, the argument masking ratio
     * (or how many characters will be masked for one character that will not be masked), and the argument
     * masking character.
     * @param toBeMasked The string to be masked.
     * @param maskingRatio The masking ratio. (characters masked/ characters not masked).
     * @param maskingChar The masking character. (will replace the original masked characters).
     * @return The masked string.
     */
    static String mask(String toBeMasked, int maskingRatio, String maskingChar){
        int spaceCount = 0;
        for (char c : toBeMasked.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        StringBuilder builder = new StringBuilder(toBeMasked);
        Random random = new Random();
        int length = toBeMasked.length();
        int i = length - spaceCount - ( (length - spaceCount) / maskingRatio) ;

        for (int j = 0; j < toBeMasked.length(); j += 2){
            if (builder.charAt(j) != ' '){
                builder.replace(j, j + 1, maskingChar);
                i--;
            }
        }

        while (i > 0){
            int stringIndex = random.nextInt(length);
            System.out.println(stringIndex);
            if ((builder.charAt(stringIndex) != ' ') && (builder.charAt(stringIndex) != maskingChar.charAt(0))){
                builder.replace(stringIndex, stringIndex + 1, maskingChar);
                i--;
            }
        }
        return builder.toString();
    }
}
