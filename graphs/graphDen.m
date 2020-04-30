dataSorted=load('Sorted.txt');
dataFHSorted=load('First_Half_Sorted.txt');
dataSHSorted=load('Second_Half_Sorted.txt');
dataRandSorted=load('Random_Sorted.txt');
dataReversSorted=load('Reverse_Sorted.txt');


x1=dataSorted(:,1);
y1=dataSorted(:,2);

x2=dataFHSorted(:,1);
y2=dataFHSorted(:,2);

x3=dataSHSorted(:,1);
y3=dataSHSorted(:,2);

x4=dataRandSorted(:,1);
y4=dataRandSorted(:,2);

x5=dataReversSorted(:,1);
y5=dataReversSorted(:,2);


figure;
plot(x2,y2,'r-');
title("First Half Sorted");
figure;
plot(x3,y3,'r-');
title("Second Half Sorted");
figure;
plot(x4,y4,'r-');
title("Random Sorted");
figure;
plot(x5,y5,'r-');
title("Reverse Sorted");
figure;
plot(x1,y1,'r-');
title("Sorted");
