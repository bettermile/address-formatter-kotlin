@file:OptIn(ExperimentalForeignApi::class)

package com.bettermile.addressformatter.mustache

import cocoapods.GRMustache.GRMustacheContext
import cocoapods.GRMustache.GRMustacheRendering
import cocoapods.GRMustache.GRMustacheRenderingProtocol
import cocoapods.GRMustache.GRMustacheTag
import kotlinx.cinterop.BooleanVarOf
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import platform.Foundation.NSError

internal actual fun MustacheLambda(lambda: (String) -> String): MustacheLambda {
    val p = checkNotNull(
        GRMustacheRendering.renderingObjectWithBlock { tag: GRMustacheTag?,
                                                       context: GRMustacheContext?,
                                                       htmlSafe: CPointer<BooleanVarOf<Boolean>>?,
                                                       error: CPointer<ObjCObjectVar<NSError?>>? ->
            val content = checkNotNull(tag).renderContentWithContext(context, htmlSafe, error)
            lambda(checkNotNull(content))
        }
    )
    return IosMustacheLambda(p)
}

internal class IosMustacheLambda(val protocol: GRMustacheRenderingProtocol) : MustacheLambda
