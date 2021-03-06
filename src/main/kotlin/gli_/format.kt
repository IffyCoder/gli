package gli_

import gli_.detail.Cap.*
import gli_.detail.has
import gli_.detail.tableFormatInfos
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * Created by elect on 02/04/17.
 */

/** Texture data format */
enum class Format {

    UNDEFINED,

    RG4_UNORM_PACK8,
    RGBA4_UNORM_PACK16,
    BGRA4_UNORM_PACK16,
    R5G6B5_UNORM_PACK16,
    B5G6R5_UNORM_PACK16,
    RGB5A1_UNORM_PACK16,
    BGR5A1_UNORM_PACK16,
    A1RGB5_UNORM_PACK16,

    R8_UNORM_PACK8,
    R8_SNORM_PACK8,
    R8_USCALED_PACK8,
    R8_SSCALED_PACK8,
    R8_UINT_PACK8,
    R8_SINT_PACK8,
    R8_SRGB_PACK8,

    RG8_UNORM_PACK8,
    RG8_SNORM_PACK8,
    RG8_USCALED_PACK8,
    RG8_SSCALED_PACK8,
    RG8_UINT_PACK8,
    RG8_SINT_PACK8,
    RG8_SRGB_PACK8,

    RGB8_UNORM_PACK8,
    RGB8_SNORM_PACK8,
    RGB8_USCALED_PACK8,
    RGB8_SSCALED_PACK8,
    RGB8_UINT_PACK8,
    RGB8_SINT_PACK8,
    RGB8_SRGB_PACK8,

    BGR8_UNORM_PACK8,
    BGR8_SNORM_PACK8,
    BGR8_USCALED_PACK8,
    BGR8_SSCALED_PACK8,
    BGR8_UINT_PACK8,
    BGR8_SINT_PACK8,
    BGR8_SRGB_PACK8,

    RGBA8_UNORM_PACK8,
    RGBA8_SNORM_PACK8,
    RGBA8_USCALED_PACK8,
    RGBA8_SSCALED_PACK8,
    RGBA8_UINT_PACK8,
    RGBA8_SINT_PACK8,
    RGBA8_SRGB_PACK8,

    BGRA8_UNORM_PACK8,
    BGRA8_SNORM_PACK8,
    BGRA8_USCALED_PACK8,
    BGRA8_SSCALED_PACK8,
    BGRA8_UINT_PACK8,
    BGRA8_SINT_PACK8,
    BGRA8_SRGB_PACK8,

    RGBA8_UNORM_PACK32,
    RGBA8_SNORM_PACK32,
    RGBA8_USCALED_PACK32,
    RGBA8_SSCALED_PACK32,
    RGBA8_UINT_PACK32,
    RGBA8_SINT_PACK32,
    RGBA8_SRGB_PACK32,

    RGB10A2_UNORM_PACK32,
    RGB10A2_SNORM_PACK32,
    RGB10A2_USCALED_PACK32,
    RGB10A2_SSCALED_PACK32,
    RGB10A2_UINT_PACK32,
    RGB10A2_SINT_PACK32,

    BGR10A2_UNORM_PACK32,
    BGR10A2_SNORM_PACK32,
    BGR10A2_USCALED_PACK32,
    BGR10A2_SSCALED_PACK32,
    BGR10A2_UINT_PACK32,
    BGR10A2_SINT_PACK32,

    R16_UNORM_PACK16,
    R16_SNORM_PACK16,
    R16_USCALED_PACK16,
    R16_SSCALED_PACK16,
    R16_UINT_PACK16,
    R16_SINT_PACK16,
    R16_SFLOAT_PACK16,

    RG16_UNORM_PACK16,
    RG16_SNORM_PACK16,
    RG16_USCALED_PACK16,
    RG16_SSCALED_PACK16,
    RG16_UINT_PACK16,
    RG16_SINT_PACK16,
    RG16_SFLOAT_PACK16,

    RGB16_UNORM_PACK16,
    RGB16_SNORM_PACK16,
    RGB16_USCALED_PACK16,
    RGB16_SSCALED_PACK16,
    RGB16_UINT_PACK16,
    RGB16_SINT_PACK16,
    RGB16_SFLOAT_PACK16,

    RGBA16_UNORM_PACK16,
    RGBA16_SNORM_PACK16,
    RGBA16_USCALED_PACK16,
    RGBA16_SSCALED_PACK16,
    RGBA16_UINT_PACK16,
    RGBA16_SINT_PACK16,
    RGBA16_SFLOAT_PACK16,

    R32_UINT_PACK32,
    R32_SINT_PACK32,
    R32_SFLOAT_PACK32,

    RG32_UINT_PACK32,
    RG32_SINT_PACK32,
    RG32_SFLOAT_PACK32,

    RGB32_UINT_PACK32,
    RGB32_SINT_PACK32,
    RGB32_SFLOAT_PACK32,

    RGBA32_UINT_PACK32,
    RGBA32_SINT_PACK32,
    RGBA32_SFLOAT_PACK32,

    R64_UINT_PACK64,
    R64_SINT_PACK64,
    R64_SFLOAT_PACK64,

    RG64_UINT_PACK64,
    RG64_SINT_PACK64,
    RG64_SFLOAT_PACK64,

    RGB64_UINT_PACK64,
    RGB64_SINT_PACK64,
    RGB64_SFLOAT_PACK64,

    RGBA64_UINT_PACK64,
    RGBA64_SINT_PACK64,
    RGBA64_SFLOAT_PACK64,

    RG11B10_UFLOAT_PACK32,
    RGB9E5_UFLOAT_PACK32,

    D16_UNORM_PACK16,
    D24_UNORM_PACK32,
    D32_SFLOAT_PACK32,
    S8_UINT_PACK8,
    D16_UNORM_S8_UINT_PACK32,
    D24_UNORM_S8_UINT_PACK32,
    D32_SFLOAT_S8_UINT_PACK64,

    RGB_DXT1_UNORM_BLOCK8,
    RGB_DXT1_SRGB_BLOCK8,
    RGBA_DXT1_UNORM_BLOCK8,
    RGBA_DXT1_SRGB_BLOCK8,
    RGBA_DXT3_UNORM_BLOCK16,
    RGBA_DXT3_SRGB_BLOCK16,
    RGBA_DXT5_UNORM_BLOCK16,
    RGBA_DXT5_SRGB_BLOCK16,
    R_ATI1N_UNORM_BLOCK8,
    R_ATI1N_SNORM_BLOCK8,
    RG_ATI2N_UNORM_BLOCK16,
    RG_ATI2N_SNORM_BLOCK16,
    RGB_BP_UFLOAT_BLOCK16,
    RGB_BP_SFLOAT_BLOCK16,
    RGBA_BP_UNORM_BLOCK16,
    RGBA_BP_SRGB_BLOCK16,

    RGB_ETC2_UNORM_BLOCK8,
    RGB_ETC2_SRGB_BLOCK8,
    RGBA_ETC2_UNORM_BLOCK8,
    RGBA_ETC2_SRGB_BLOCK8,
    RGBA_ETC2_UNORM_BLOCK16,
    RGBA_ETC2_SRGB_BLOCK16,
    R_EAC_UNORM_BLOCK8,
    R_EAC_SNORM_BLOCK8,
    RG_EAC_UNORM_BLOCK16,
    RG_EAC_SNORM_BLOCK16,

    RGBA_ASTC_4X4_UNORM_BLOCK16,
    RGBA_ASTC_4X4_SRGB_BLOCK16,
    RGBA_ASTC_5X4_UNORM_BLOCK16,
    RGBA_ASTC_5X4_SRGB_BLOCK16,
    RGBA_ASTC_5X5_UNORM_BLOCK16,
    RGBA_ASTC_5X5_SRGB_BLOCK16,
    RGBA_ASTC_6X5_UNORM_BLOCK16,
    RGBA_ASTC_6X5_SRGB_BLOCK16,
    RGBA_ASTC_6X6_UNORM_BLOCK16,
    RGBA_ASTC_6X6_SRGB_BLOCK16,
    RGBA_ASTC_8X5_UNORM_BLOCK16,
    RGBA_ASTC_8X5_SRGB_BLOCK16,
    RGBA_ASTC_8X6_UNORM_BLOCK16,
    RGBA_ASTC_8X6_SRGB_BLOCK16,
    RGBA_ASTC_8X8_UNORM_BLOCK16,
    RGBA_ASTC_8X8_SRGB_BLOCK16,
    RGBA_ASTC_10X5_UNORM_BLOCK16,
    RGBA_ASTC_10X5_SRGB_BLOCK16,
    RGBA_ASTC_10X6_UNORM_BLOCK16,
    RGBA_ASTC_10X6_SRGB_BLOCK16,
    RGBA_ASTC_10X8_UNORM_BLOCK16,
    RGBA_ASTC_10X8_SRGB_BLOCK16,
    RGBA_ASTC_10X10_UNORM_BLOCK16,
    RGBA_ASTC_10X10_SRGB_BLOCK16,
    RGBA_ASTC_12X10_UNORM_BLOCK16,
    RGBA_ASTC_12X10_SRGB_BLOCK16,
    RGBA_ASTC_12X12_UNORM_BLOCK16,
    RGBA_ASTC_12X12_SRGB_BLOCK16,

    RGB_PVRTC1_8X8_UNORM_BLOCK32,
    RGB_PVRTC1_8X8_SRGB_BLOCK32,
    RGB_PVRTC1_16X8_UNORM_BLOCK32,
    RGB_PVRTC1_16X8_SRGB_BLOCK32,
    RGBA_PVRTC1_8X8_UNORM_BLOCK32,
    RGBA_PVRTC1_8X8_SRGB_BLOCK32,
    RGBA_PVRTC1_16X8_UNORM_BLOCK32,
    RGBA_PVRTC1_16X8_SRGB_BLOCK32,
    RGBA_PVRTC2_4X4_UNORM_BLOCK8,
    RGBA_PVRTC2_4X4_SRGB_BLOCK8,
    RGBA_PVRTC2_8X4_UNORM_BLOCK8,
    RGBA_PVRTC2_8X4_SRGB_BLOCK8,

    RGB_ETC_UNORM_BLOCK8,
    RGB_ATC_UNORM_BLOCK8,
    RGBA_ATCA_UNORM_BLOCK16,
    RGBA_ATCI_UNORM_BLOCK16,

    L8_UNORM_PACK8,
    A8_UNORM_PACK8,
    LA8_UNORM_PACK8,
    L16_UNORM_PACK16,
    A16_UNORM_PACK16,
    LA16_UNORM_PACK16,

    BGR8_UNORM_PACK32,
    BGR8_SRGB_PACK32,

    RG3B2_UNORM_PACK8;

    val i = ordinal

    companion object {
        val FIRST: Format = RG4_UNORM_PACK8
        val LAST: Format = RG3B2_UNORM_PACK8
        val COUNT = LAST.i - FIRST.i + 1
        infix fun of(i: Int): Format = values().find { it.i == i } ?: UNDEFINED
    }

    val isValid: Boolean
        get() = this != UNDEFINED

    /** *get_format_info */
    val formatInfo: detail.FormatInfo
        get() = tableFormatInfos[i - FIRST.i].also { assert(isValid) }

    val bitsPerPixel by lazy { formatInfo.blockSize * 8 / (formatInfo.blockExtend.x * formatInfo.blockExtend.y * formatInfo.blockExtend.z) }

    val isCompressed by lazy { formatInfo.flags has COMPRESSED_BIT }
    val isS3tcCompressed by lazy { this in RGB_DXT1_UNORM_BLOCK8..RGBA_DXT5_SRGB_BLOCK16 }

    val isSrgb by lazy { formatInfo.flags has COLORSPACE_SRGB_BIT }

    val blockSize by lazy { formatInfo.blockSize }
    val blockExtend by lazy { formatInfo.blockExtend }

    val componentCount by lazy { formatInfo.component }

    val isUnsigned by lazy { formatInfo.flags has UNSIGNED_BIT }
    val isSigned by lazy { formatInfo.flags has SIGNED_BIT }

    val isInteger by lazy { formatInfo.flags has INTEGER_BIT }
    val isSignedInteger by lazy { isInteger && isSigned }
    val isUnsignedInteger by lazy { isInteger && isUnsigned }

    val isFloat by lazy { formatInfo.flags has FLOAT_BIT }

    val isNormalized by lazy { formatInfo.flags has NORMALIZED_BIT }
    val isUnorm by lazy { isNormalized && isUnsigned }
    val isSnorm by lazy { isNormalized && isSigned }

    val isPacked by lazy { formatInfo.flags has PACKED8_BIT || formatInfo.flags has PACKED16_BIT || formatInfo.flags has PACKED32_BIT }

    val isDepth by lazy { formatInfo.flags has DEPTH_BIT }
    val isStencil by lazy { formatInfo.flags has STENCIL_BIT }
    val isDepthStencil by lazy {
        isDepth && isStencil
    }

    operator fun rangeTo(that: Format): FormatRange = FormatRange(this, that)

    class FormatRange(override val start: Format, override val endInclusive: Format) : ClosedRange<Format>, Iterable<Format> {
        constructor(start: Format, to: Int) : this(start, values().first { it.i == to })

        override fun iterator() = object : Iterator<Format> {
            var next = start
            override fun next(): Format {
                val res = next
                if (next != endInclusive) next = Format.values()[next.ordinal + 1]
                return res
            }

            override fun hasNext() = next < endInclusive
        }

        override fun contains(value: Format): Boolean =
                value.i in start.i..endInclusive.i
    }

    operator fun minus(format: Format): Int = i - format.i
}


/** Represent the source of a channel   */
enum class Swizzle {
    RED,
    GREEN,
    BLUE,
    ALPHA,
    ZERO,
    ONE;

    val i = ordinal

    companion object {
        val FIRST = RED
        val LAST = ONE
        val CHANNEL_FIRST = RED
        val CHANNEL_LAST = ALPHA
        val COUNT = LAST.i - FIRST.i + 1
        infix fun of(i: Int): Swizzle = values().first { it.i == i }
    }

    val isChannel: Boolean
        get () = this in CHANNEL_FIRST..CHANNEL_LAST
}

data class Swizzles(var r: Swizzle, var g: Swizzle, var b: Swizzle, var a: Swizzle) {
    constructor(x: Int, y: Int, z: Int, w: Int) : this(Swizzle of x, Swizzle of y, Swizzle of z, Swizzle of w)
    constructor(swizzle: Swizzle) : this(swizzle, swizzle, swizzle, swizzle)
    constructor(swizzles: Swizzles) : this(swizzles.r, swizzles.g, swizzles.b, swizzles.a)
    constructor() : this(Swizzle.RED, Swizzle.GREEN, Swizzle.BLUE, Swizzle.ALPHA)

    operator fun get(i: Int) = when (i) {
        0 -> r
        1 -> g
        2 -> b
        3 -> a
        else -> throw Error()
    }

    infix fun to(buffer: ByteBuffer): ByteBuffer = buffer.putInt(0, r.i).putInt(1, g.i).putInt(2, b.i).putInt(3, a.i)
    infix fun to(intBuffer: IntBuffer): IntBuffer = intBuffer.put(0, r.i).put(1, g.i).put(2, b.i).put(3, a.i)
}

