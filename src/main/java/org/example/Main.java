package org.example;

import algorithms.MergeSort;
import algorithms.QuickSort;
import metrics.Metrics;

import java.util.Arrays;

public class Main{
    public static void main(String[] args){

        int [] originalArray = {42,15,8,99,23,4,16,8,100,1};
        System.out.println("исходный массив" + Arrays.toString(originalArray));
        System.out.println("----------");


        int[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
        Metrics mergeMetrics = new Metrics();

        MergeSort.sort(mergeArray, mergeMetrics);

        // Тест merge
        System.out.println("После MergeSort: " + Arrays.toString(mergeArray));
        System.out.println("Сравнений: " + mergeMetrics.getComparisons());
        System.out.println("Глубина рекурсии: " + mergeMetrics.getMaxDepth());
        System.out.println("--------");

        // Тест QuickSort
        int[] quickArray = Arrays.copyOf(originalArray, originalArray.length);
        Metrics quickMetrics = new Metrics();

        QuickSort.sort(quickArray, quickMetrics);

        System.out.println("После QuickSort: " + Arrays.toString(quickArray));
        System.out.println("Сравнений: " + quickMetrics.getComparisons());
        System.out.println("Глубина рекурсии: " + quickMetrics.getMaxDepth());

    }



}

