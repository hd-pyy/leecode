# 最大堆
## 1、建堆
### 找到最后一个非叶子节点
### 从后向前 执行下潜
### 代码实现
    最后一个非叶子节点的索引为 size/2 -1
    左孩子索引 left = parent * 2 + 1
    右孩子索引 right = left + 1

    // 建堆
    private void heapify(){
        // 找到最后非叶子节点 (size >>> 1) - 1
        for (int i = size/2 -1; i >=0 ; i--) {
            // 下潜
            down(i);
        }
    }

    /**
     * 下潜
     * 与两个孩子较大者进行比较
     * @param parent 要下潜的索引值
     */
    private void down(int parent) {
        int left = parent*2 +1;
        int right = left +1;
        int max = parent;
        if (left < size && array[left]>array[max]){
            max = left;
        }
        if (right < size && array[right]>array[max]){
            max = right;
        }
        if (max != parent){
            // 找到了更大的孩子 交换 交换max与parent
            swap(max,parent);
            // 递归 下潜
            down(max);
        }
    }

    /**
     * 交换
     * @param max
     * @param parent
     */
    private void swap(int max,int parent){
        int t = array[max];
        array[max] = array[parent];
        array[parent] = t;

    }
