%%Mücahit Tanac?o?lu 150115006
function [] = drawGraph(name)
dataSorted=load(name+'_sorted.txt');
dataFHSorted=load(name+'_firstHalfSorted.txt');
dataSHSorted=load(name+'_secondHalfSorted.txt');
dataRandSorted=load(name+'_randomSorted.txt');
dataReversSorted=load(name+'_reverseSorted.txt');

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
f = @(x) x.*log(x);
f_1 = @(x) x.^2;
f_2 = @(x) x;
x = 0:1:1000;

figure;
subplot(5,2,1);
plot(x2,y2,'r-',x,f(x),"b--",x,f_1(x),"m--",x,f_2(x),"g--");
ylim([0 4000])
legend("test","nlog(n)","n^2","n");
title(name+" First Half Sorted");
xlabel("Array Size");
ylabel("Base case ex. count");

subplot(5,2,2);
plot(x3,y3,'r-',x,f(x),"b--",x,f_1(x),"m--",x,f_2(x),"g--");
legend("test","nlog(n)","n^2","n");
ylim([0 4000])
title(name+" Second Half Sorted");
xlabel("Array Size");
ylabel("Base case ex. count");

subplot(5,2,3);
plot(x4,y4,'r-',x,f(x),"b--",x,f_1(x),"m--",x,f_2(x),"g--");
legend("test","nlog(n)","n^2","n");
ylim([0 4000])
title(name+" Random Sorted");
xlabel("Array Size");
ylabel("Base case ex. count");

subplot(5,2,4);
plot(x5,y5,'r-',x,f(x),"b--",x,f_1(x),"m--",x,f_2(x),"g--");
ylim([0 4000])
legend("test","nlog(n)","n^2","n");
title(name+" Reverse Sorted");
xlabel("Array Size");
ylabel("Base case ex. count");

subplot(5,2,5);
plot(x1,y1,'r-',x,f(x),"b--",x,f_1(x),"m--",x,f_2(x),"g--");
ylim([0 4000])
legend("test","nlog(n)","n^2","n");
title(name+" Sorted");
xlabel("Array Size");
ylabel("Base case ex. count");
end

