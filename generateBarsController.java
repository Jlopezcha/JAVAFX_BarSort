package com.example.newfirstfxproject.controllers;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class generateBarsController {

    //controls/buttons
    public BarChart barChart;
    public Button genBarsButton;

    public Button sortButton;
    public ChoiceBox<String> cBox = new ChoiceBox<>();


    public Label timeLabel;

    final int BARS_LIMIT = 800;


    String[] sortingAlgo = {
            "Default",
            "Bubble Sort",
            "Selection Sort",
            "Insertion Sort",
            "Shell Sort",
            "Quick Sort",
            "Merge Sort",
            "Radix Sort"
    };


    ArrayList<Integer> randomNumArray = new ArrayList<>(BARS_LIMIT);

    //Object used to generate random numbers
    Random randomNumber = new Random();

    //method gets called instantly when fxml objects load up
    public void initialize(){
        //Populate the randomNumArray
        for(int i = 0; i < BARS_LIMIT; i++){
            int randomNum = randomNumber.nextInt(99) + 1;
            randomNumArray.add(randomNum);
        }

        cBox.getItems().addAll(sortingAlgo);
        cBox.setValue("Default");

        System.out.println(randomNumArray.size());
        System.out.println(randomNumArray);

        sortButton.setDisable(true);
    }

    //is there a way to hide the x axis -- use x axis to replace position
    XYChart.Series series1 = new XYChart.Series();



    //event controller for generate bars button
    public void onClickEvent(MouseEvent mouseEvent) {
        System.out.println("Bar graph generated");

        //XYChart.Series series1 = new XYChart.Series();
        /* old code this is adding it manually but the method is much faster
        series1.getData().add(new XYChart.Data("1", randomNumArray.get(0)));
        series1.getData().add(new XYChart.Data("2", randomNumArray.get(1)));
        series1.getData().add(new XYChart.Data("3", randomNumArray.get(2)));
        series1.getData().add(new XYChart.Data("4", randomNumArray.get(3)));
        series1.getData().add(new XYChart.Data("5", randomNumArray.get(4)));
        series1.getData().add(new XYChart.Data("6", randomNumArray.get(5)));
        series1.getData().add(new XYChart.Data("7", randomNumArray.get(6)));
        series1.getData().add(new XYChart.Data("8", randomNumArray.get(7)));
        series1.getData().add(new XYChart.Data("9", randomNumArray.get(8)));
        series1.getData().add(new XYChart.Data("10", randomNumArray.get(9)));
         */

        createXYChart(randomNumArray);



        barChart.getData().addAll(series1);

        cBox.setDisable(true);
        genBarsButton.setDisable(true);
        sortButton.setDisable(false);

    }


    //event controller for the sort button
    public void onClickSort(MouseEvent mouseEvent) {

        System.out.println(randomNumArray.size());

        //Time functionality to determine execution time
        //Basically it gets the current time and subtracts it with the time after the execution
        long start = System.currentTimeMillis();
        //Collections.sort(randomNumArray);
        //bubbleSort(randomNumArray);

        callSortAlgorithm(randomNumArray, cBox.getValue());

        long end = System.currentTimeMillis();

        float sec = (end - start)/ 1000F;

        System.out.println(cBox.getValue() + " took: " + sec + " seconds");

        System.out.println(randomNumArray);

        timeLabel.setText(cBox.getValue() + " took: " + sec + "s");

        //need to update the values or reset the bargraph
        series1.getData().clear();

        createXYChart(randomNumArray);

        barChart.setData(FXCollections.observableArrayList(series1));

        sortButton.setDisable(true);


    }


    //create a label to display the time

    //Used to create the chart using the random number arraylist
    public void createXYChart(ArrayList<Integer> numArray){
        for (int i = 0; i < numArray.size(); i++){
            int k = i + 1;
            series1.getData().add(new XYChart.Data(String.valueOf(k), randomNumArray.get(i)));
        }
    }


    //need a logic method to take in what option the user selected


    //calling method is a logic method that will call the correct method based on the choicebox
    public static void callSortAlgorithm(ArrayList<Integer> numArr, String choiceBoxOption){
        if(choiceBoxOption.equals("Default"))
            Collections.sort(numArr);
        if(choiceBoxOption.equals("Bubble Sort"))
            bubbleSort(numArr);
        if(choiceBoxOption.equals("Selection Sort"))
            selectionSort(numArr);
        if(choiceBoxOption.equals("Insertion Sort"))
            insertionSort(numArr);
        if(choiceBoxOption.equals("Shell Sort"))
            shellSort(numArr);
        if(choiceBoxOption.equals("Quick Sort"))
            quickSort(numArr);
        if(choiceBoxOption.equals("Merge Sort"))
            mergeSort(numArr);
        if(choiceBoxOption.equals("Radix Sort"))
            radixSort(numArr);


    }

    //sorting methods
    public static void bubbleSort(ArrayList<Integer> numArray){
        int temp;

        for(int i = 0; i < numArray.size(); i++){
            for(int k = i + 1; k < numArray.size(); k++){
                if(numArray.get(i) > numArray.get(k)){
                    //store the small value
                    temp = numArray.get(k);

                    //swap the values moving bigger one to the right
                    numArray.set(k, numArray.get(i));

                    //swap lower value and move it to the left
                    numArray.set(i, temp);
                }
            }
        }

    }


    public static void selectionSort(ArrayList<Integer> numArray)
    {
        //Loops through the outside
        for (int i = 0; i < numArray.size() - 1; ++i) {

            // Find index of smallest remaining element
            int indexSmallest = i;

            //Goes through each one only considering current value. This finds the smallest value
            for (int j = i + 1; j < numArray.size(); ++j) {

                if (numArray.get(j) < numArray.get(indexSmallest)) {
                    indexSmallest = j;
                }
            }

            // Swap numbers[i] and numbers[indexSmallest] indexsmallest is J

            //temp has the larger value i
            int temp = numArray.get(i);

            //get the i element and set the value to the smallest one
            numArray.set(i, numArray.get(indexSmallest));

            //Swap J to hold the larger value temp
            numArray.set(indexSmallest, temp);

        }

    }

    public static void insertionSort(ArrayList<Integer> numArray)
    {
        int temp = 0;

        //Notice i starts at 1
        for (int i = 1; i < numArray.size(); ++i) {
            int j = i;
            // Insert numbers[i] which is represented by j into sorted part
            // stopping once numbers[i] in correct position
            while (j > 0 && numArray.get(j) < numArray.get(j - 1)) {

                // Swap numbers[j] and numbers[j - 1]

                //temp holds the smaller number
                temp = numArray.get(j);

                //moves the bigger number to the right
                numArray.set(j, numArray.get(j - 1));

                //insert the small value to the left
                numArray.set((j - 1), temp);
                --j;
            }
        }
    }



    public static void interleavedInsertionSort(ArrayList<Integer> numArray, int startIndex, int gap){
        int k = 0;
        int temp = 0;

        for (int i = startIndex + gap; i < numArray.size(); i = i + gap) {
            k = i;
            while (k - gap >= startIndex && numArray.get(k) < numArray.get(k - gap)) {
                temp = numArray.get(k);
                numArray.set(k, numArray.get(k - gap));
                numArray.set(k - gap, temp);
                k = k - gap;
            }
        }

    }

    public static void shellSort(ArrayList<Integer> numArray){
        //need a math expression that can calculate the half of 2
        //int gapSize = (int) Math.floor((numArray.size()/2) / 2);

        //test this s
        for(int gap = numArray.size()/2; gap > 0; gap /= 2){

            for(int k = 0; k < gap; k++){
                interleavedInsertionSort(numArray, k, gap);
            }

        }
    }


    public static int partition(ArrayList<Integer> numArray, int lowIndex, int highIndex) {
        // Pick middle element as pivot
        int midpoint = lowIndex + (highIndex - lowIndex) / 2;
        int pivot = numArray.get(midpoint);

        boolean done = false;
        while (!done) {
            // Increment lowIndex while numbers[lowIndex] < pivot
            //checking to see if elements on the left are smaller than pivot hence the while loop
            while (numArray.get(lowIndex) < pivot) {
                lowIndex += 1;
            }

            // Decrement highIndex while pivot < numbers[highIndex]
            //checking to see if elements on the right are larger than pivot hence the while loop
            while (pivot < numArray.get(highIndex)) {
                highIndex -= 1;
            }

            // If zero or one elements remain, then all numbers are
            // partitioned. Return highIndex.
            if (lowIndex >= highIndex) {
                done = true;
            }
            else {
                // Swap numbers[lowIndex] and numbers[highIndex]
                //I think temp has the larger value
                int temp = numArray.get(lowIndex);

                //I think the high index has the small value and we will make it go to the left side using low index
                numArray.set(lowIndex, numArray.get(highIndex));

                //temp holding the large value will be moved to the right which is the high index
                numArray.set(highIndex, temp);

                // Update lowIndex and highIndex
                lowIndex += 1;
                highIndex -= 1;
            }
        }

        return highIndex;
    }

    public static void quickSort(ArrayList<Integer> numArray, int lowIndex, int highIndex){

        //stops the recursion
        if(lowIndex >= highIndex){
            return;
        }

        // gets the result of the partition method which is the pivot index
        int otherLowIndex = partition(numArray, lowIndex, highIndex);

        //recursion for the left side or low partition
        quickSort(numArray, lowIndex, otherLowIndex);

        //recursion for the right side or high partition
        quickSort(numArray, otherLowIndex + 1, highIndex);

    }

    //Method overload of quick sort that only has 1 parameter
    public static void quickSort(ArrayList<Integer> numArray){
        quickSort(numArray, 0, numArray.size() - 1);
    }


    public static void merge(ArrayList<Integer> numArray, ArrayList<Integer> lHalf,  ArrayList<Integer> rHalf){
        //these account for the 3 arrays that are passed i is for leftHalf, j is rightHalf, k is numArray
        int i = 0, j = 0, k = 0;

        //both conditions have to be true
        while(i < lHalf.size() && j < rHalf.size()) {
            //sorts
            if(lHalf.get(i) <= rHalf.get(j)) {
                numArray.set(k, lHalf.get(i));
                i++;
            }
            else {
                numArray.set(k, rHalf.get(j));
                j++;
            }
            k++;
        }

        //account for left overs either from the left side or right bc loop above does not increment everything evenly
        //both these loops will ensure that the increments are full and array has been checked
        while(i < lHalf.size()) {
            numArray.set(k, lHalf.get(i));
            i++;
            k++;
        }

        while(j < rHalf.size()) {
            numArray.set(k, rHalf.get(j));
            j++;
            k++;
        }
    }

    public static void mergeSort(ArrayList<Integer> numArray){

        //ends the recursion if array has less than 2 elements
        if(numArray.size() < 2)
            return;

        int mid = numArray.size()/2;
        ArrayList<Integer> leftHalf = new ArrayList<>(mid);
        ArrayList<Integer> rightHalf = new ArrayList<>(numArray.size() - mid);

        for(int i = 0; i < mid; i++){
            leftHalf.add(i, numArray.get(i));
        }

        for(int i = mid; i < numArray.size(); i++){
            rightHalf.add(i - mid, numArray.get(i));
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(numArray, leftHalf, rightHalf);

    }


    public static void modifiedCountingSort(ArrayList<Integer> numArray, int place){
        int size = numArray.size();
        ArrayList<Integer> output = new ArrayList<>(size + 1);

        //initialize to 0
        for (int i = 0; i < size + 1; ++i) {
            output.add(i, 0);
        }

        // Find the largest element of the array
        int max = numArray.get(0);
        for (int i = 1; i < size; i++) {
            if (numArray.get(i) > max)
                max = numArray.get(i);
        }

        ArrayList<Integer> count = new ArrayList<>(max  + 1);

        //initialize to 0
        for (int i = 0; i < max; ++i) {
            count.add(i, 0);
        }

        // count of each element
        for (int i = 0; i < size; i++) {
            //count[(numArray[i] / place) % 10]++;
            count.set((numArray.get(i)/ place) % 10, count.get(((numArray.get(i)/ place) % 10)) + 1);
        }

        int c = 0;
        // Store the cumulative count of each array
        //10 is the 10 buckets
        for (int i = 1; i < 10; i++) {
            //count[i] += count[i - 1];

            c = count.get(i);
            count.set(i, c += count.get(i - 1));
        }

        // place the elements in output array
        //notice the i-- this goes from top to bottom reason why is at the end it will be ascending not descending
        for (int i = size - 1; i >= 0; i--) {

            //output[count[(numArray[i] / place) % 10] - 1] = numArray[i];
            output.set(count.get(((numArray.get(i) / place) % 10) ) - 1  , numArray.get(i));
            //count[(numArray[i] / place) % 10]--;
            count.set((numArray.get(i)/ place) % 10, count.get(((numArray.get(i)/ place) % 10)) - 1);
        }

        // Copy the sorted elements into original array
        for (int i = 0; i < size; i++) {
            //numArray[i] = output[i];
            numArray.set(i, output.get(i));
        }
    }

    public static void radixSort(ArrayList<Integer> numArray){
        int max = numArray.get(0);
        for(int i = 1; i < numArray.size(); i++){
            if(numArray.get(i) > max){
                max = numArray.get(i);
            }
        }


        for(int place = 1; max/place > 0; place *= 10){
            modifiedCountingSort(numArray, place);
        }

    }


    /* default code
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

     */





}