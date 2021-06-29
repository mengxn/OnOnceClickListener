# OnOnceClickListener
防止快速重复点击

## 使用说明
### 1. 普通模式(默认第一次有效)
``` kotlin
loginBtn.setOnClickListener(OnOnceClickListener {
    // do something
})
```

### 2. 最后一次有效
``` kotlin
loginBtn.setOnClickListener(OnOnceClickListener(OnOnceClickListener.Strategy.LAST) {
    // do something
})
```

### 3. 设置间隔时间
``` kotlin
loginBtn.setOnClickListener(OnOnceClickListener(interval = 500) {
    // do something
})
```
