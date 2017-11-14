# Combiner
---
现在一台服务器上进行合并,局部reduce.减少对网络的负载.

# Partitioner
---
对key进行求hash,判断给哪一个reduce处理

# Shuffle
---
洗牌. 归整数据,然后再给reduce处理

# Sort
排序