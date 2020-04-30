syms f(x)
x=[1 5 0 32 8];
y=[10 1 654 32 100];
[~, index] = sort(x);
F = griddedInterpolant(x(index), y(index));
