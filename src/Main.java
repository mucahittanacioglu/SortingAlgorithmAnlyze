import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static int test=0;
    static ArrayList<ResultList> result_data;
    static double startTime,elapsedTime;
    static String tempName,pathToGraphfolder="graphs/";
    static BufferedWriter bwSorted,bwReverseSorted,bwRandomSorted,bwFirstHSorted,bwSecondHSorted ;

    public static void main(String[] args)throws Exception
    {

        int baseSize=10;
        int howMuchList=10;


        result_data = new ArrayList();
        ArrayList<TestList> test_data = new ArrayList();

        createTestData(test_data,baseSize,howMuchList);

        writeResults(result_data);


    }

    private static void writeResults(ArrayList<ResultList> result_data) throws Exception{
        bwSorted = new BufferedWriter(new FileWriter(new File(pathToGraphfolder+"Sorted.txt")));
        bwReverseSorted = new BufferedWriter(new FileWriter(new File(pathToGraphfolder+"Reverse_Sorted.txt")));
        bwRandomSorted = new BufferedWriter(new FileWriter(new File(pathToGraphfolder+"Random_Sorted.txt")));
        bwFirstHSorted = new BufferedWriter(new FileWriter(new File(pathToGraphfolder+"First_Half_Sorted.txt")));
        bwSecondHSorted = new BufferedWriter(new FileWriter(new File(pathToGraphfolder+"Second_Half_Sorted.txt")));

        for(ResultList currentList : result_data ) {
            if(currentList.name.contains("sorted_")){
                tempName=currentList.name.replace("sorted_","");
                bwSorted.write(tempName+"\t"+currentList.data);
                bwSorted.newLine();
            }else if(currentList.name.contains("reverseSorted_")){
                tempName=currentList.name.replace("reverseSorted_","");
                bwReverseSorted.write(tempName+"\t"+currentList.data);
                bwReverseSorted.newLine();
            }else if(currentList.name.contains("randomSorted_")){
                tempName=currentList.name.replace("randomSorted_","");
                bwRandomSorted.write(tempName+"\t"+currentList.data);
                bwRandomSorted.newLine();
            }else if(currentList.name.contains("firstHalfSorted_")){
                tempName=currentList.name.replace("firstHalfSorted_","");
                bwFirstHSorted.write(tempName+"\t"+currentList.data);
                bwFirstHSorted.newLine();
            }else{
                tempName=currentList.name.replace("secondHalfSorted_","");
                bwSecondHSorted.write(tempName+"\t"+currentList.data);
                bwSecondHSorted.newLine();
            }

            System.out.println(currentList.name +": "+currentList.data);
        }
        bwFirstHSorted.close();
        bwSecondHSorted.close();
        bwRandomSorted.close();
        bwReverseSorted.close();
        bwSorted.close();
    }

    public static void createTestData(ArrayList<TestList> test_data,int baseSize, int howMuchList){
        int increasingElementfactor=2;
        int size;
        for(int i = 0;i<howMuchList;i++) {
            size=(int)Math.pow(increasingElementfactor,i)*baseSize;
            createSortedTestData(test_data,size);
            for(TestList currentList : test_data ){

                mergeSort(currentList.data,currentList.data.length);
                result_data.add(new ResultList("MergeSort_"+currentList.name,test));
                test=0;

                createSortedTestData(test_data,size);
                insertionSort(currentList.data);
                result_data.add(new ResultList("InsertionSort_"+currentList.name,test));
                test=0;


            }
            test_data.clear();
            Runtime.getRuntime().gc();

        }for(int i = 0;i<howMuchList;i++) {
            size=(int)Math.pow(increasingElementfactor,i)*baseSize;
            createReverseSortedTestData(test_data, size);
            for(TestList currentList : test_data ){
                mergeSort(currentList.data,currentList.data.length);
                result_data.add(new ResultList("MergeSort_"+currentList.name,test));
                test=0;

                createReverseSortedTestData(test_data, size);
                insertionSort(currentList.data);
                result_data.add(new ResultList("InsertionSort_"+currentList.name,test));
                test=0;

            }
            test_data.clear();
            Runtime.getRuntime().gc();
        }for(int i = 0;i<howMuchList;i++) {
            size=(int)Math.pow(increasingElementfactor,i)*baseSize;
            createRandomSortedTestData(test_data, size);
            for(TestList currentList : test_data ){

                mergeSort(currentList.data,currentList.data.length);
                result_data.add(new ResultList("MergeSort_"+currentList.name,test));
                test=0;

                createRandomSortedTestData(test_data,size);
                insertionSort(currentList.data);
                result_data.add(new ResultList("InsertionSort_"+currentList.name,test));
                test=0;

            }
            test_data.clear();
            Runtime.getRuntime().gc();
        }for(int i = 0;i<howMuchList;i++) {
            size=(int)Math.pow(increasingElementfactor,i)*baseSize;
            createFirstHalfSortedTestData(test_data, size);
            for(TestList currentList : test_data ){

                mergeSort(currentList.data,currentList.data.length);
                result_data.add(new ResultList("MergeSort_"+currentList.name,test));
                test=0;

                createFirstHalfSortedTestData(test_data,size);
                insertionSort(currentList.data);
                result_data.add(new ResultList("InsertionSort_"+currentList.name,test));
                test=0;

            }
            test_data.clear();
            Runtime.getRuntime().gc();
        }for(int i = 0;i<howMuchList;i++) {
            size=(int)Math.pow(increasingElementfactor,i)*baseSize;
            createSecondHalfSortedTestData(test_data, size);
            for(TestList currentList : test_data ){

                mergeSort(currentList.data,currentList.data.length);
                result_data.add(new ResultList("MergeSort_"+currentList.name,test));
                test=0;

                createSecondHalfSortedTestData(test_data,size);
                insertionSort(currentList.data);
                result_data.add(new ResultList("InsertionSort_"+currentList.name,test));
                test=0;

            }
            test_data.clear();
            Runtime.getRuntime().gc();
        }
    }
    private static void createSortedTestData(ArrayList<TestList> test_data, int size) {

        TestList test_data_sorted = new TestList("sorted_"+size,size);

        for(int i = 0; i<size;i++){
            test_data_sorted.data[i]=i;
        }
        test_data.add(test_data_sorted);
    }
    private static void createReverseSortedTestData(ArrayList<TestList> test_data, int size) {

        TestList test_data_sorted = new TestList("reverseSorted_"+size,size);

        for(int i = size,y=0; y<size; i--,y++){
            test_data_sorted.data[y]=i;
        }
        test_data.add(test_data_sorted);
    }
    private static void createRandomSortedTestData(ArrayList<TestList> test_data, int size) {

        TestList test_data_sorted = new TestList("randomSorted_"+size,size);

        for(int i = 0; i<size; i++){
            test_data_sorted.data[i]=new Random().nextInt();
        }
        test_data.add(test_data_sorted);
    }
    private static void createFirstHalfSortedTestData(ArrayList<TestList> test_data, int size) {

        TestList test_data_sorted = new TestList("firstHalfSorted_"+size,size);

        for(int i = 0; i<size/2; i++){
            test_data_sorted.data[i]=i;
        }
        for(int i = size/2; i<size; i++){
            test_data_sorted.data[i]=new Random().nextInt();
        }
        test_data.add(test_data_sorted);
    }
    private static void createSecondHalfSortedTestData(ArrayList<TestList> test_data, int size) {

        TestList test_data_sorted = new TestList("secondHalfSorted_"+size,size);

        for(int i = 0; i<size/2; i++){
            test_data_sorted.data[i]=new Random().nextInt();
        }
        for(int i = size/2; i<size; i++){
            test_data_sorted.data[i]=i;
        }
        test_data.add(test_data_sorted);
    }

    private static void selectionSort(int[] array) {
        int n = array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (array[j] < array[min_idx])
                    min_idx = j;
                test++;
            }
            // Swap the found minimum element with the first
            // element
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }

    private static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
                test++;
            }
            array[j + 1] = key;
        }

    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);

    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
            test++;
        }
        while (i < left) {
            a[k++] = l[i++];
            test++;
        }
        while (j < right) {
            a[k++] = r[j++];
            test++;
        }
    }
}
 class TestList {

    public String name;
    public int[] data;
    public TestList(String name, int size){
        this.name = name;
        this.data = new int[size];
    }
}
class ResultList {

    public String name;
    public double data;
    public ResultList(String name, double data){
        this.name = name;
        this.data = data;
    }
}
