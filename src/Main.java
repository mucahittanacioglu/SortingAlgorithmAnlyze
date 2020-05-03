//Mucahit Tanacioglu 150115006
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class Main {
    static double baseCaseCounter =0;//counter used for algorithm execution time mesure.
    static ArrayList<ResultList> result_data;
    static String tempName,pathToGraphfolder = "report/outputs/";//output directory
    static final int CUTOFF = 10;//quick select motp algorithm uses this as point that array sizes below this will be sorted with insertion sort

    public static void main(String[] args)throws Exception
    {

        int baseSize=1;//test will use different size of arrays base on this(sizes differ by 1)
        int howMuchList=1000;//this denotes how many array will be created for each type of array and also denotes lenght of array(from 1 to this number=

        result_data = new ArrayList();//result array
        ArrayList<TestList> test_data = new ArrayList();//test array

        startTest(test_data,baseSize,howMuchList);//starting test

        //sorting result data by their algorithm name to bettter console print
        result_data.sort(new Comparator<ResultList>() {
            @Override
            public int compare(ResultList o1, ResultList o2) {
                return o1.name.split("_")[0].compareTo(o2.name.split("_")[0]);
            }
        });
        //writing result
        writeResults(result_data);

    }
    //this methods creates test arrays with methods below and after algorithm proceed its removed to avoid alot of ram usage
    public static void startTest(ArrayList<TestList> test_data, int baseSize, int howMuchList){
        Integer[] objectArray;//The code I found requires Comparable array this is for that algorithm
        int size;//array size for creating data
        MaxHeap maxHeap ;//Max heap object for max heap algorithm
        TestList currentList;//reference to hold list that going to process by algorithms
        /*
           Here 5 for loop 1 for each array type(sorted,reverse sorted,random,fh sorted,second half sorted)
           Each for loop almost identical except creating tes array
           Each for loop proceed to algrotihms with order Merge Sort,Insertion Sort,Max heap,quick select without sort,and quick sort motp
         */
        for(int i = 0;i<howMuchList;i++) {
            size= i + baseSize;

            createSortedTestData(test_data,size);
            currentList=test_data.get(0);
            mergeSort(currentList.data,currentList.data.length);
            result_data.add(new ResultList("MergeSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSortedTestData(test_data,size);
            currentList=test_data.get(0);
            insertionSort(currentList.data);
            result_data.add(new ResultList("InsertionSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSortedTestData(test_data,size);
            currentList=test_data.get(0);
            maxHeap = new MaxHeap(currentList.data.length);
            for(int k:currentList.data)
                maxHeap.insert(k);
            for(int y = 0;y <currentList.data.length/2;y++)
                maxHeap.extractMax();
            result_data.add(new ResultList("MaxHeap_"+currentList.name, maxHeap.baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();
            maxHeap=null;

            createSortedTestData(test_data,size);
            currentList=test_data.get(0);
            kthSmallest(currentList.data, 0,currentList.data.length-1, (int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectWithoutSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSortedTestData(test_data,size);
            currentList=test_data.get(0);
            objectArray = new Integer[currentList.data.length];
            for(int j = 0; j < currentList.data.length; j++)
                objectArray[j] = Integer.valueOf(currentList.data[j]);
            quickSelect(objectArray,(int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectMOTP_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();


            Runtime.getRuntime().gc();

        }
        for(int i = 0;i<howMuchList;i++) {
            size= i+ baseSize;

            createReverseSortedTestData(test_data, size);
            currentList=test_data.get(0);
            mergeSort(currentList.data,currentList.data.length);
            result_data.add(new ResultList("MergeSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createReverseSortedTestData(test_data,size);
            currentList=test_data.get(0);
            insertionSort(currentList.data);
            result_data.add(new ResultList("InsertionSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createReverseSortedTestData(test_data,size);
            currentList=test_data.get(0);
            maxHeap = new MaxHeap(currentList.data.length);
            for(int k:currentList.data)
                maxHeap.insert(k);
            for(int y = 0;y <currentList.data.length/2;y++)
                maxHeap.extractMax();
            result_data.add(new ResultList("MaxHeap_"+currentList.name, maxHeap.baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();
            maxHeap=null;

            createReverseSortedTestData(test_data,size);
            currentList=test_data.get(0);
            kthSmallest(currentList.data, 0,currentList.data.length-1, (int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectWithoutSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createReverseSortedTestData(test_data,size);
            currentList=test_data.get(0);
            objectArray = new Integer[currentList.data.length];
            for(int j = 0; j < currentList.data.length; j++)
                objectArray[j] = Integer.valueOf(currentList.data[j]);
            quickSelect(objectArray,(int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectMOTP_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            Runtime.getRuntime().gc();
        }
        for(int i = 0;i<howMuchList;i++) {
            size=i+ baseSize;

            createRandomSortedTestData(test_data, size);
            currentList=test_data.get(0);
            mergeSort(currentList.data,currentList.data.length);
            result_data.add(new ResultList("MergeSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createRandomSortedTestData(test_data,size);
            currentList=test_data.get(0);
            insertionSort(currentList.data);
            result_data.add(new ResultList("InsertionSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createRandomSortedTestData(test_data,size);
            currentList=test_data.get(0);
            maxHeap = new MaxHeap(currentList.data.length);
            for(int k:currentList.data)
                maxHeap.insert(k);
            for(int y = 0;y <currentList.data.length/2;y++)
                maxHeap.extractMax();
            result_data.add(new ResultList("MaxHeap_"+currentList.name, maxHeap.baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();
            maxHeap=null;

            createRandomSortedTestData(test_data,size);
            currentList=test_data.get(0);
            kthSmallest(currentList.data, 0,currentList.data.length-1, (int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectWithoutSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createRandomSortedTestData(test_data,size);
            currentList=test_data.get(0);
            objectArray = new Integer[currentList.data.length];
            for(int j = 0; j < currentList.data.length; j++)
                objectArray[j] = Integer.valueOf(currentList.data[j]);
            quickSelect(objectArray,(int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectMOTP_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            Runtime.getRuntime().gc();
        }
        for(int i = 0;i<howMuchList;i++) {
            size=i+ baseSize;

            createFirstHalfSortedTestData(test_data, size);
            currentList=test_data.get(0);
            mergeSort(currentList.data,currentList.data.length);
            result_data.add(new ResultList("MergeSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createFirstHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            insertionSort(currentList.data);
            result_data.add(new ResultList("InsertionSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createFirstHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            maxHeap = new MaxHeap(currentList.data.length);
            for(int k:currentList.data)
                maxHeap.insert(k);
            for(int y = 0;y <currentList.data.length/2;y++)
                maxHeap.extractMax();
            result_data.add(new ResultList("MaxHeap_"+currentList.name, maxHeap.baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();
            maxHeap=null;

            createFirstHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            kthSmallest(currentList.data, 0,currentList.data.length-1, (int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectWithoutSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createFirstHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            objectArray = new Integer[currentList.data.length];
            for(int j = 0; j < currentList.data.length; j++)
                objectArray[j] = Integer.valueOf(currentList.data[j]);
            quickSelect(objectArray,(int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectMOTP_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            Runtime.getRuntime().gc();
        }
        for(int i = 0;i<howMuchList;i++) {
            size=i+ baseSize;

            createSecondHalfSortedTestData(test_data, size);
            currentList=test_data.get(0);
            mergeSort(currentList.data,currentList.data.length);
            result_data.add(new ResultList("MergeSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSecondHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            insertionSort(currentList.data);
            result_data.add(new ResultList("InsertionSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSecondHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            maxHeap = new MaxHeap(currentList.data.length);
            for(int k:currentList.data)
                maxHeap.insert(k);
            for(int y = 0;y <currentList.data.length/2;y++)
                maxHeap.extractMax();
            result_data.add(new ResultList("MaxHeap_"+currentList.name, maxHeap.baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();
            maxHeap=null;

            createSecondHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            kthSmallest(currentList.data, 0,currentList.data.length-1, (int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectWithoutSort_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            createSecondHalfSortedTestData(test_data,size);
            currentList=test_data.get(0);
            objectArray = new Integer[currentList.data.length];
            for(int j = 0; j < currentList.data.length; j++)
                objectArray[j] = Integer.valueOf(currentList.data[j]);
            quickSelect(objectArray,(int)Math.ceil(currentList.data.length/2.0));
            result_data.add(new ResultList("QuickSelectMOTP_"+currentList.name, baseCaseCounter));
            baseCaseCounter =0;
            test_data.clear();

            Runtime.getRuntime().gc();
        }
    }
    //this method writes result of test into text file with name of algorithm and used array type
    private static void writeResults(ArrayList<ResultList> result_data) throws Exception{
        FileWriter fw = null;

        for(ResultList currentList : result_data ) {
            if(currentList.name.contains("sorted_")){
                if(currentList.name.contains("MergeSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MergeSort_sorted.txt"),true);
                    tempName=currentList.name.replace("MergeSort_sorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("InsertionSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"InsertionSort_sorted.txt"),true);
                    tempName=currentList.name.replace("InsertionSort_sorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("MaxHeap")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MaxHeap_sorted.txt"),true);
                    tempName=currentList.name.replace("MaxHeap_sorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectWithoutSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectWithoutSort_sorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectWithoutSort_sorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectMOTP")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectMOTP_sorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectMOTP_sorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }

                fw.close();
            }else if(currentList.name.contains("reverseSorted_")){
                if(currentList.name.contains("MergeSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MergeSort_reverseSorted.txt"),true);
                    tempName=currentList.name.replace("MergeSort_reverseSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("InsertionSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"InsertionSort_reverseSorted.txt"),true);
                    tempName=currentList.name.replace("InsertionSort_reverseSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("MaxHeap")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MaxHeap_reverseSorted.txt"),true);
                    tempName=currentList.name.replace("MaxHeap_reverseSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectWithoutSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectWithoutSort_reverseSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectWithoutSort_reverseSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectMOTP")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectMOTP_reverseSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectMOTP_reverseSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }

                fw.close();
            }else if(currentList.name.contains("randomSorted_")){
                if(currentList.name.contains("MergeSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MergeSort_randomSorted.txt"),true);
                    tempName=currentList.name.replace("MergeSort_randomSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("InsertionSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"InsertionSort_randomSorted.txt"),true);
                    tempName=currentList.name.replace("InsertionSort_randomSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("MaxHeap")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MaxHeap_randomSorted.txt"),true);
                    tempName=currentList.name.replace("MaxHeap_randomSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectWithoutSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectWithoutSort_randomSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectWithoutSort_randomSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectMOTP")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectMOTP_randomSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectMOTP_randomSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }

                fw.close();
            }else if(currentList.name.contains("firstHalfSorted_")){
                if(currentList.name.contains("MergeSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MergeSort_firstHalfSorted.txt"),true);
                    tempName=currentList.name.replace("MergeSort_firstHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("InsertionSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"InsertionSort_firstHalfSorted.txt"),true);
                    tempName=currentList.name.replace("InsertionSort_firstHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("MaxHeap")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MaxHeap_firstHalfSorted.txt"),true);
                    tempName=currentList.name.replace("MaxHeap_firstHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectWithoutSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectWithoutSort_firstHalfSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectWithoutSort_firstHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectMOTP")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectMOTP_firstHalfSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectMOTP_firstHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }
                fw.close();
            }else if(currentList.name.contains("secondHalfSorted_")){
                if(currentList.name.contains("MergeSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MergeSort_secondHalfSorted.txt"),true);
                    tempName=currentList.name.replace("MergeSort_secondHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("InsertionSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"InsertionSort_secondHalfSorted.txt"),true);
                    tempName=currentList.name.replace("InsertionSort_secondHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("MaxHeap")){
                    fw = new FileWriter(new File(pathToGraphfolder+"MaxHeap_secondHalfSorted.txt"),true);
                    tempName=currentList.name.replace("MaxHeap_secondHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectWithoutSort")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectWithoutSort_secondHalfSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectWithoutSort_secondHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }else if(currentList.name.contains("QuickSelectMOTP")){
                    fw = new FileWriter(new File(pathToGraphfolder+"QuickSelectMOTP_secondHalfSorted.txt"),true);
                    tempName=currentList.name.replace("QuickSelectMOTP_secondHalfSorted_","");
                    fw.write(tempName+"\t"+currentList.data+"\n");
                }
                fw.close();
            }

            System.out.println(currentList.name +": "+currentList.data);
        }

    }

    //these methods creates arrays as their mentions----------------
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
            test_data_sorted.data[i]=new Random().nextInt(size*10);
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

    //insertion sort algorithm-----------------------
    private static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
            baseCaseCounter++;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
                baseCaseCounter++;
            }
            array[j + 1] = key;
        }

    }
    //merge sort algorithm----------------
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
            baseCaseCounter++;
        }
        while (i < left) {
            a[k++] = l[i++];
            baseCaseCounter++;
        }
        while (j < right) {
            a[k++] = r[j++];
            baseCaseCounter++;
        }
    }
    //Quick select Median of three pivot algorithm----------------------
    public static void quickSelect( Comparable [ ] a, int k ) {
        quickSelect( a, 0, a.length - 1, k );
    }

    private static void quickSelect( Comparable [ ] a, int low, int high, int k ) {
        if( low + CUTOFF > high )
            insertionSort( a, low, high );
        else {
            // Sort low, middle, high
            int middle = ( low + high ) / 2;
            if(baseCaseCounter++>=0 && a[ middle ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, middle );
            if(baseCaseCounter++>=0 && a[ high ].compareTo( a[ low ] ) < 0 )
                swapReferences( a, low, high );
            if(baseCaseCounter++>=0 &&  a[ high ].compareTo( a[ middle ] ) < 0  )
                swapReferences( a, middle, high );

            // Place pivot at position high - 1
            swapReferences( a, middle, high - 1 );
            Comparable pivot = a[ high - 1 ];

            // Begin partitioning
            int i, j;
            for( i = low, j = high - 1; ; ) {
                while(baseCaseCounter++>=0 && a[ ++i ].compareTo( pivot ) < 0 )
                    ;
                while(baseCaseCounter++>=0 && pivot.compareTo( a[ --j ] ) < 0 )
                    ;
                if(baseCaseCounter++>=0 && i >= j )
                    break;
                swapReferences( a, i, j );

            }

            // Restore pivot
            swapReferences( a, i, high - 1 );

            // Recurse; only this part changes
            if( k <= i )
                quickSelect( a, low, i - 1, k );
            else if( k > i + 1 )
                quickSelect( a, i + 1, high, k );
        }
    }

    private static void insertionSort( Comparable [ ] a, int low, int high ) {
        for( int p = low + 1; p <= high; p++ ) {
            Comparable tmp = a[ p ];
            int j;

            for( j = p; j > low && tmp.compareTo( a[ j - 1 ] ) < 0; j-- ) {
                a[j] = a[j - 1];
                baseCaseCounter++;
            }
            a[ j ] = tmp;
        }
    }

    public static final void swapReferences( Object [ ] a, int index1, int index2 ) {
        Object tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
        baseCaseCounter++;
    }
    //Quick select algorithm---------------------
    public static int kthSmallest(int a[], int left,int right, int k) {
        while (left <= right)
        {

            // Partition a[left..right] around a pivot
            // and find the position of the pivot
            int pivotIndex = partition(a, left, right);

            // If pivot itself is the k-th smallest element
            if (pivotIndex == k - 1)
                return a[pivotIndex];

                // If there are more than k-1 elements on
                // left of pivot, then k-th smallest must be
                // on left side.
            else if (pivotIndex > k - 1)
                right = pivotIndex - 1;

                // Else k-th smallest is on right side.
            else
                left = pivotIndex + 1;
        }
        return -1;
    }

    public static int partition(int arr[],int low, int high) {
        int temp;
        int pivot = arr[low];
        int i = (high - 1) , p1 = low +1 ;
        for (int j = low+ 1; j <= high; j++)
        {
            if ( baseCaseCounter++>=0 && arr[j] < pivot)
            {
                if(baseCaseCounter++>=0 && j != p1) {
                    temp = arr[p1] ;
                    arr[p1] = arr[j] ;
                    arr[j] = temp ;
                    baseCaseCounter++;
                }
                p1++;
            }
        }
        arr[low] = arr[p1-1] ;
        arr[p1-1] = pivot ;

        return (p1-1);
    }




}
//class to name int arrays for writing them with order later
 class TestList {

    public String name;
    public int[] data;
    public TestList(String name, int size){
        this.name = name;
        this.data = new int[size];
    }
 }
//result list for store algorithm name and its execution mesurument
class ResultList {

    public String name;
    public double data;
    public ResultList(String name, double data){
        this.name = name;
        this.data = data;
    }
}
//Max Heap class for Max heap algorithm
 class MaxHeap {
    private int[] Heap;
    private int size;
    private int maxsize;
     double baseCaseCounter ;
    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity.
    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
        baseCaseCounter=0;
    }

    // Returns position of parent
    private int parent(int pos)
    {
        return pos / 2;
    }

    // Below two functions return left and
    // right children.
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Returns true of given node is leaf
    private boolean isLeaf(int pos)
    {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // A recursive function to max heapify the given
    // subtree. This function assumes that the left and
    // right subtrees are already heapified, we only need
    // to fix the root.
    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)] ||
                Heap[pos] < Heap[rightChild(pos)]) {
            baseCaseCounter++;
            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    // Inserts a new element to max heap
    public void insert(int element)
    {
        Heap[++size] = element;

        // Traverse up and fix violated property
        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
            baseCaseCounter++;
        }
    }

    public void print()
    {
        for (int i = 1; i < size / 2; i++) {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " +
                    Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }

    // Remove an element from max heap
    public int extractMax()
    {
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }
}