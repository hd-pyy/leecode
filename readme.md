# 好好学习 天天向上



### 正则表达式





### HashMap 与 HashSet

#### Hashset的底层是HashMap

#### HashMap的方法

HashMap 有很多常用的方法，以下是主要的 API 方法：

##### 基础操作方法

###### 1. 添加和更新元素
```java
HashMap<String, Integer> map = new HashMap<>();

// put - 添加键值对，如果键已存在则更新值
map.put("apple", 1);
map.put("banana", 2);

// putIfAbsent - 只有当键不存在时才放入
map.putIfAbsent("apple", 5); // 不会更新，因为"apple"已存在
map.putIfAbsent("orange", 3); // 会添加

// compute - 根据现有值计算新值
map.compute("apple", (key, value) -> value + 1); // apple的值变为2
```

###### 2. 获取元素
```java
// get - 根据键获取值
Integer value = map.get("apple"); // 返回1

// getOrDefault - 获取值，如果键不存在返回默认值
Integer val = map.getOrDefault("grape", 0); // 返回0

// containsKey - 检查是否包含指定的键
boolean hasKey = map.containsKey("apple"); // true

// containsValue - 检查是否包含指定的值
boolean hasValue = map.containsValue(2); // true
```

###### 3. 删除元素
```java
// remove - 根据键删除
map.remove("apple");

// remove - 根据键和值删除（只有键值都匹配时才删除）
map.remove("banana", 3); // 只有banana的值是3时才删除

// clear - 清空所有元素
map.clear();
```

###### 4. 遍历和集合视图
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

// keySet - 获取所有键的集合
for (String key : map.keySet()) {
    System.out.println(key);
}

// values - 获取所有值的集合
for (Integer value : map.values()) {
    System.out.println(value);
}

// entrySet - 获取所有键值对的集合（最常用）
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// forEach - Java 8+ 的遍历方式
map.forEach((key, value) -> System.out.println(key + ": " + value));
```

##### 实用工具方法

###### 5. 批量操作
```java
HashMap<String, Integer> map1 = new HashMap<>();
map1.put("A", 1);
map1.put("B", 2);

HashMap<String, Integer> map2 = new HashMap<>();
map2.put("C", 3);
map2.put("D", 4);

// putAll - 将另一个Map的所有元素放入当前Map
map1.putAll(map2);

// replaceAll - 替换所有值
map1.replaceAll((key, value) -> value * 2);
```

###### 6. 合并和计算
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("apple", 1);

// merge - 合并值
map.merge("apple", 1, Integer::sum); // apple的值变为2
map.merge("banana", 1, Integer::sum); // 添加banana=1

// computeIfAbsent - 如果键不存在，计算新值
map.computeIfAbsent("orange", k -> 5); // 添加orange=5

// computeIfPresent - 如果键存在，计算新值
map.computeIfPresent("apple", (k, v) -> v + 10); // apple的值变为12
```

##### 信息查询方法

###### 7. 状态查询
```java
HashMap<String, Integer> map = new HashMap<>();

// size - 获取元素个数
int size = map.size();

// isEmpty - 检查是否为空
boolean empty = map.isEmpty();

// equals - 比较两个Map是否相等
boolean isEqual = map.equals(anotherMap);

// hashCode - 获取哈希码
int hashCode = map.hashCode();
```



#### HashSet 的常用方法如下：

##### 基础操作方法

###### 1. 添加元素
```java
HashSet<String> set = new HashSet<>();

// add - 添加元素，如果元素已存在则返回false
boolean added1 = set.add("apple");    // true
boolean added2 = set.add("apple");    // false
boolean added3 = set.add("banana");   // true

// addAll - 批量添加
Set<String> fruits = Set.of("orange", "grape");
set.addAll(fruits);
```

###### 2. 删除元素
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("orange");

// remove - 删除指定元素
boolean removed1 = set.remove("apple");   // true
boolean removed2 = set.remove("grape");   // false

// removeAll - 批量删除
set.removeAll(Set.of("banana", "grape"));

// removeIf - 条件删除（Java 8+）
set.removeIf(fruit -> fruit.startsWith("a"));

// clear - 清空所有元素
set.clear();
```

###### 3. 查询元素
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");

// contains - 检查是否包含元素
boolean hasApple = set.contains("apple");     // true
boolean hasGrape = set.contains("grape");     // false

// containsAll - 检查是否包含所有元素
boolean hasAll = set.containsAll(Set.of("apple", "banana")); // true
```

###### 4. 遍历元素
```java
HashSet<String> set = new HashSet<>();
set.add("A");
set.add("B");
set.add("C");

// 方法1：增强for循环
for (String element : set) {
    System.out.println(element);
}

// 方法2：迭代器
Iterator<String> iterator = set.iterator();
while (iterator.hasNext()) {
    String element = iterator.next();
    System.out.println(element);
}

// 方法3：forEach（Java 8+）
set.forEach(element -> System.out.println(element));

// 方法4：方法引用
set.forEach(System.out::println);
```

##### 集合操作方法

###### 5. 集合运算
```java
HashSet<String> set1 = new HashSet<>(Set.of("A", "B", "C"));
HashSet<String> set2 = new HashSet<>(Set.of("B", "C", "D"));

// retainAll - 取交集
set1.retainAll(set2); // set1变为 [B, C]

// addAll - 取并集（重新初始化）
set1 = new HashSet<>(Set.of("A", "B", "C"));
set1.addAll(set2); // set1变为 [A, B, C, D]

// removeAll - 取差集
set1 = new HashSet<>(Set.of("A", "B", "C"));
set1.removeAll(set2); // set1变为 [A]
```

##### 信息查询方法

###### 6. 状态查询
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");

// size - 获取元素个数
int size = set.size();        // 2

// isEmpty - 检查是否为空
boolean empty = set.isEmpty(); // false

// equals - 比较两个Set是否相等
boolean isEqual = set.equals(Set.of("apple", "banana")); // true
```

###### 转换方法

###### 7. 转换为其他集合
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");

// toArray - 转换为数组
Object[] array1 = set.toArray();
String[] array2 = set.toArray(new String[0]);

// 转换为List
List<String> list = new ArrayList<>(set);

// 转换为不可修改的Set
Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
```

##### 在实际问题中的应用示例

###### 示例1：去重
```java
public class HashSetExample {
    public static void main(String[] args) {
        // 数组去重
        int[] numbers = {1, 2, 2, 3, 4, 4, 5};
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        
        for (int num : numbers) {
            uniqueNumbers.add(num);
        }
        
        System.out.println(uniqueNumbers); // [1, 2, 3, 4, 5]
    }
}
```

###### 示例2：查找共同元素
```java
public class CommonElements {
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        HashSet<Integer> set2 = new HashSet<>(Set.of(4, 5, 6, 7, 8));
        
        // 找交集
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("交集: " + intersection); // [4, 5]
        
        // 找差集
        HashSet<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("差集: " + difference); // [1, 2, 3]
    }
}
```

###### 示例3：在算法题中的应用
```java
class Solution {
    // 判断字符串是否包含重复字符
    public boolean hasDuplicateChars(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) { // 如果add返回false，说明字符已存在
                return true;
            }
        }
        return false;
    }
    
    // 查找两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);
        
        set1.retainAll(set2); // 取交集
        
        return set1.stream().mapToInt(i -> i).toArray();
    }
}
```

HashSet 基于 HashMap 实现，因此具有很好的性能，特别适合需要快速查找、去重的场景。







### 字符串





### 异或特点

​	1、不同数相异或 -> 1

​	2、相同 -> 0

​	3、任何数与0异或 -> 1

### 滑动窗口

![image-20251105220807568](C:\Users\17651\AppData\Roaming\Typora\typora-user-images\image-20251105220807568.png)

H



## JDK

## 在java中 char类型可以隐式转为int 值为 字符所对应的ascii

![image-20251105221231783](C:\Users\17651\AppData\Roaming\Typora\typora-user-images\image-20251105221231783.png)

