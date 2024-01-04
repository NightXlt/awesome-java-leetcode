package com.blankj.medium._300

import com.blankj.ext.print
import kotlin.math.max


class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        var res = 0
        for (num in nums) {
            var left = 0
            // right 指向最新的右边界，最终会指向插入位置前面一位
            var right = res
            while (left < right) {
                val mid = (left + right) shr 1
                when {
                    dp[mid] >= num -> right = mid
                    else -> left = mid + 1
                }
            }
            dp[left] = num
            // 如果比 dp 最后的元素都大，则更新长度
            if (res == right) {
                res++
            }
        }
        return res
    }

    fun lengthOfLISWithDP(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }
        if (nums.isEmpty()) return 0
        var res = Int.MIN_VALUE
        for (i in nums.indices) {
            for (j in 0..<i) {
                if (nums[j] < nums[i]) {
                    dp[i] = max(dp[i], dp[j] + 1)
                }
            }
            res = max(dp[i], res)
        }
        return res
    }
}

fun main() {
    Solution().lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)).print()
}