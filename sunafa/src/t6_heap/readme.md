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

## 2、上浮
    核心：从下倒上 找到被插入元素所在位置
### 1、先往堆底添加元素
    a、 offered > arr[p]
            1、到了堆顶停止
            2、offered < arr[p]
### 代码实现
    // 在底部添加一个元素
    // 将 offered元素上浮 带扩容
    public boolean offer(int offered) {
        if (size == arr.length) {
    //            size++;
    arr = Arrays.copyOf(arr, arr.length + 1);
    //            offer(offered);
    }
    up(offered);
    // 上浮成功
    size++;
    return true;
    }

    // 上浮
    public void up(int offered) {
        // child指针 一直上浮 当指针为零时 到达堆顶
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            // 当offered大于父元素 则交换
            if (offered > arr[parent]) {
                // 把父元素所在位置腾出来
                arr[child] = arr[parent];

            } else break;
            // 移动child指针到parent
            child = parent;
        }
        // 给child元素赋值
        arr[child] = offered;
    }

## 3、堆排序算法
    核心：最大堆最大堆 堆顶就是最大值 把最大值依次取出即可
    实现：1、先取出堆顶值
        2、将堆顶与堆底进行交换
        3、将堆大小减一
        循环 直至 size == 0;