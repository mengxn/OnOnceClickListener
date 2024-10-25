package me.codego.view

import android.view.View
import kotlin.math.max

/**
 * 拦截快速点击事件
 * @author mengxn
 * @date 2021/6/29
 */
open class OnOnceClickListener(
    private val strategy: Strategy = Strategy.FIRST,
    private var interval: Long = 300,
    private val onOnceClick: (view: View) -> Unit
) : View.OnClickListener {

    enum class Strategy {
        /**
         * 仅第一次有效
         */
        FIRST,

        /**
         * 仅最后一次有效
         */
        LAST,

        /**
         * 对快速事件进行截流
         */
        THROTTLE,
    }

    companion object {

        /**
         * 默认拦截间隔300ms
         */
        private const val DEFAULT_EFFECTIVE_TIME = 300L

    }

    private var mLastTime: Long = 0
    private lateinit var mCallback: Runnable

    init {
        interval = max(interval, DEFAULT_EFFECTIVE_TIME)
    }

    override fun onClick(v: View) {
        val currentTimeMillis = System.currentTimeMillis()
        when (strategy) {
            Strategy.FIRST -> {
                if (currentTimeMillis - mLastTime > interval) {
                    onOnceClick(v)
                }
                mLastTime = currentTimeMillis
            }
            Strategy.LAST -> {
                if (this::mCallback.isInitialized) {
                    v.removeCallbacks(mCallback)
                } else {
                    mCallback = Runnable { onOnceClick(v) }
                }
                v.postDelayed(mCallback, interval)
                mLastTime = currentTimeMillis
            }
            Strategy.THROTTLE -> {
                if (currentTimeMillis - mLastTime > interval) {
                    onOnceClick(v)
                    mLastTime = currentTimeMillis
                }
            }
        }
    }
}

fun View.setOnOnceClickListener(
    strategy: OnOnceClickListener.Strategy = OnOnceClickListener.Strategy.FIRST,
    interval: Long = 300,
    onOnceClick: (view: View) -> Unit
) {
    setOnClickListener(OnOnceClickListener(strategy, interval, onOnceClick))
}

fun View.setOnOnceClickListener(
    interval: Long = 300,
    onOnceClick: (view: View) -> Unit
) {
    setOnClickListener(OnOnceClickListener(OnOnceClickListener.Strategy.FIRST, interval, onOnceClick))
}