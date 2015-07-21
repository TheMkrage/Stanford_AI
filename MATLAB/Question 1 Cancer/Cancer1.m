[data, varnames, casenames] = tblread('training_set.tab', '\t');

zerocount = 0;
zerosum = 0;
onecount = 0;
onesum = 0;

lowesthighvalue = 1;
highestlowvalue = 1;
for colCount = 1: length(varnames)
    if strcmp(varnames(colCount,6), '0')
        zerocount = zerocount + 1;
    else
        onecount = onecount + 1 ;
    end
end

for rowCount = 1: 4399
    zerosum = 0;
    onesum = 0;
    zeroarray = zeros(1, 1);
    onearray = zeros(1, 1);
    for colCount = 1: 131
        if strcmp(varnames(colCount,6), '0')
            zerosum = zerosum + data(rowCount, colCount);
            zeroarray(rowCount) = data(rowCount, colCount);
        else
            onesum = onesum + data(rowCount, colCount);
            onearray(rowCount) = data(rowCount, colCount);
        end
    end
    fisher(rowCount, 1) = rowCount;
    fisher(rowCount, 2) = ((onesum/onecount) - (zerosum/zerocount)) / (( var(zeroarray, 1) + var(onearray, 1) ));
end
fisher = sortrows(fisher, 2);
fisher
%w(4, 5)



