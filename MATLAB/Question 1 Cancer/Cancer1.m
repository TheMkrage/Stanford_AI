
x = tdfread('training_set.tab')
w = struct2table(x)
colnames = w.Properties.VariableNames
rownames = w.Properties.RowNames
colnames{1,5}

zerocount = 0
zerosum = 0
onecount = 0
onesum = 0
for rowCount = 2: 132
    for colCount = 2: 4400
        
        sl = length(colnames{1,colCount})
        pl = 1
        s = colnames{1,colCount}
        pat = '0'

        if (sl >= pl && strcmp(s(sl-pl+1:sl), pat)) || isempty(pat);
            zerocount = zerocount + 1
            zerosum = zerosum + w{rowCount, colCount}
            zeroArray{1, zerocount} = w{rowCount, colCount}
        else
           onecount = onecount + 1 
           onesum = onesum + w{rowCount, colCount}
           oneArray{1, onecount} = w{rowCount, colCount}
        end
        
    end
    
end
%w(4, 5)



