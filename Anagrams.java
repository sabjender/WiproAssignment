package com.sab.anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Anagrams {

    public static void main(String args[]) {
        ArrayList<ArrayList<String>> words = new ArrayList<ArrayList<String>>();
        List<String> inputList = new ArrayList<String>();
        inputList.add("CAT");
        inputList.add("DOG");
        inputList.add("TAC");
        inputList.add("MAD");
        inputList.add("DAM");
        inputList.add("AMD");
        inputList.add("GOD");
        inputList.add("SET");
        ArrayList<ArrayList<String>> result = getAnagrams(inputList);
        System.out.println("Anagrams for given list:" + inputList +  "is : " +  result);

    }

    private static ArrayList<ArrayList<String>> getAnagrams(List<String> inputList) {
        HashMap<HashMap<Character, Integer>,
                ArrayList<String>>
                map = new HashMap<HashMap<Character, Integer>,
                ArrayList<String>>();

        for (String s : inputList) {
            HashMap<Character, Integer>
                    characterCountMap = new HashMap<Character, Integer>();
            for (int index = 0; index < s.length(); index++) {
                if (characterCountMap.containsKey(s.charAt(index))) {
                    int x = characterCountMap.get(s.charAt(index));
                    characterCountMap.put(s.charAt(index), ++x);
                } else {
                    characterCountMap.put(s.charAt(index), 1);
                }

            }
            if (map.containsKey(characterCountMap))
                map.get(characterCountMap).add(s);
            else {
                ArrayList<String>
                        tempList = new ArrayList<String>();
                tempList.add(s);
                map.put(characterCountMap, tempList);
            }
        }

        ArrayList<ArrayList<String>>
                result = new ArrayList<>();
        for (HashMap.Entry<HashMap<Character, Integer>, ArrayList<String>> entry : map.entrySet())
            result.add(entry.getValue());
        return result;
    }
}
