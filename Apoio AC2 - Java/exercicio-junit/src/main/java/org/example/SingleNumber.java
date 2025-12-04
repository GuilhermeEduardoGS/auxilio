package org.example;

public class SingleNumber {

    public int singleNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            boolean unico = true;

            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    unico = false;
                    break;
                }
            }

            if (unico) {
                return nums[i];
            }
        }

        throw new IllegalArgumentException("Nenhum número único encontrado!");
    }
}
