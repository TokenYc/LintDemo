package com.qianfanyun.lintlib

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.qianfanyun.lintlib.detector.*
import java.util.*

/**
 * @date on 2019-12-23  14:07
 * @author ArcherYc
 * @mail  247067345@qq.com
 */
class DangerIssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() {
            return Arrays.asList(
                StealImeiDetector.ISSUE,
                StealImsiDetector.ISSUE,
                StealPhoneNumberDetector.ISSUE,
                ListenPhoneDetector.ISSUE,
                StealGpsDetector.ISSUE,
                StealCameraDetector.ISSUE,
                StorageDetector.ISSUE
            )
        }

    override val api: Int
        get() = CURRENT_API
}