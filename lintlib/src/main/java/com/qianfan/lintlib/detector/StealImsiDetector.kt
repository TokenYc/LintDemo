package com.qianfanyun.lintlib.detector

import com.android.tools.lint.detector.api.*
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.MethodNode

/**
 * @date on 2019-12-23  14:31
 * @author ArcherYc
 * @mail  247067345@qq.com
 */
class StealImsiDetector : Detector(), ClassScanner {

    companion object {
        val ISSUE = Issue.create(
            "StealImsi",//问题 Id
            "",//问题的简单描述，会被 report 接口传入的描述覆盖
            "",//问题的详细描述
            Category.CORRECTNESS,//问题类型
            6,//问题严重程度，0~10，越大严重
            Severity.ERROR,//问题严重程度
            //Detector 和 Scope 的对应关系
            Implementation(
                StealImsiDetector::class.java,
                Scope.ALL_CLASSES_AND_LIBRARIES
            )
        )
    }
    /**
     * 返回这个 Detector 适用的 ASM 指令
     */
    override fun getApplicableAsmNodeTypes(): IntArray? {
        //这里关心的是与方法调用相关的指令，其实就是以 INVOKE 开头的指令集
        return intArrayOf(AbstractInsnNode.METHOD_INSN)
    }
    /**
     * 扫描到 Detector 适用的指令时，回调此接口
     */
    override fun checkInstruction(context: ClassContext, classNode: ClassNode, method: MethodNode, instruction: AbstractInsnNode) {
        if (instruction.opcode != Opcodes.INVOKEVIRTUAL) {
            return
        }
        val callerMethodSig = classNode.name + "." + method.name + method.desc
        val methodInsn = instruction as MethodInsnNode
        // 这里逻辑是：调用 NfcAdapter 中的任何方法都会报告异常
        if (methodInsn.name == "getSubscriberId"&&methodInsn.owner=="android/telephony/TelephonyManager") {
            val message = "SDK 中 $callerMethodSig 调用了 " +
                    "${methodInsn.owner.substringAfterLast('/')}.${methodInsn.name} 的方法，需要注意！"
            context.report(ISSUE, method, methodInsn, context.getLocation(methodInsn), message)
        }
    }
}