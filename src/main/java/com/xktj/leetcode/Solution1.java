package com.xktj.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        int [] arr= new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i]+nums[j]==target){
                    arr[0] =nums[i];
                    arr[1] =nums[j];
                    return arr;
                }
            }
        }
        return arr ;
    }

    public int[] addTowNumHash(int target,int...arr){
//        Map<Integer,Integer> map = new HashMap<>();
//
//        for (int i = 0; i < arr.length; i++) {
//            int temp = target-arr[i];
//            if (map.containsKey(temp)){
//                return new int[]{map.get(temp),i};
//            }
//            map.put(temp,i);
//        }
//        return new int[]{-1,-1};
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<arr.length;i++){
            int n = target-arr[i];
            if(map.containsKey(n)){
                return new int[]{map.get(n),i};
            }
            map.put(n,i);
        }
        return new int[]{-1,-1} ;
    }

    @Test
    public void test(){
        int[] arr = {2,5,5,11};
//        int[] ints = twoSum(arr, 10);
        int[] ints1 = addTowNumHash( 10,arr);
//        System.out.println(ints[0]+"+"+ints[1]+"="+(ints[0]+ints[1]));
        System.out.println(arr[ints1[0]]+"+"+arr[ints1[1]]+"="+(arr[ints1[0]]+arr[ints1[1]]));
    }
}
