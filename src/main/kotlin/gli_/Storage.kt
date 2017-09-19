package gli_

import gli_.buffer.destroy
import glm_.glm
import glm_.size
import glm_.vec1.Vec1i
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer

/**
 * Created by GBarbieri on 03.04.2017.
 */

class Storage {

    var layers = 0
        private set
    var faces = 0
        private set
    var levels = 0
        private set
    var blockSize = 0
        private set

    private var blockCount = Vec3i(0)

    var blockExtend = Vec3i(0)
        private set

    private var extent = Vec3i(0)

    private var data: ByteBuffer? = null

    constructor()
    constructor(storage: Storage) {
        layers = storage.layers
        faces = storage.faces
        levels = storage.levels
        blockSize = storage.blockSize
        blockCount = Vec3i(storage.blockCount)
        blockExtend = Vec3i(storage.blockExtend)
        extent = Vec3i(storage.extent)
        data = MemoryUtil.memByteBuffer(MemoryUtil.memAddress(storage.data), storage.data!!.remaining())
    }

    constructor(format: Format, extent: Vec3i, layers: Int, faces: Int, levels: Int) {
        this.layers = layers
        this.faces = faces
        this.levels = levels
        this.blockSize = format.blockSize
        this.blockCount = glm.max(extent / format.blockExtend, 1)
        this.blockExtend = format.blockExtend
        this.extent = extent

        assert(layers >= 0)
        assert(faces >= 0)
        assert(levels >= 0)
        assert(glm.all(glm.greaterThan(extent, Vec3i(0))))

        data = MemoryUtil.memCalloc(layerSize(0, faces - 1, 0, levels - 1) * layers)
    }

    fun empty() = data == null
    fun notEmpty() = data != null

    fun blockCount(level: Int): Vec3i {
        assert(level in 0 until levels)
        return glm.ceilMultiple(extent(level), blockExtend) / blockExtend
    }

    fun extent(level: Int): Vec3i {
        assert(level in 0 until levels)
        return glm.max(extent shr level, 1)
    }

    fun size() = data!!.size

    fun data() = data!!

    /** Compute the relative memory offset to access the data for a specific layer, face and level  */
    fun baseOffset(layer: Int, face: Int, level: Int): Int {

        assert(notEmpty())
        assert(layer in 0 until layers && face in 0 until faces && level in 0 until levels)

        val layerSize = layerSize(0, faces - 1, 0, levels - 1)
        val faceSize = faceSize(0, levels - 1)

        return layerSize * layer + faceSize * face + (0 until level).sumBy { levelSize(it) }
    }

    fun imageOffset(coord: Int, extend: Int): Int {
        assert(coord <= extend)
        return coord
    }

    fun imageOffset(coord: Vec1i, extend: Vec1i): Int {
        assert(glm.all(glm.lessThan(coord, extend)))
        return coord.x
    }

    fun imageOffset(coord: Vec2i, extend: Vec2i): Int {
        assert(glm.all(glm.lessThan(coord, extend)))
        return coord.x + coord.y * extend.x
    }

    fun imageOffset(coord: Vec3i, extent: Vec3i): Int {
        assert(glm.all(glm.lessThan(coord, extent)))
        return coord.x + coord.y * extent.x + coord.z * extent.x * extent.y
    }

    /** Copy a subset of a specific image of a texture  */
    fun copy(storageSrc: Storage,
             layerSrc: Int, faceSrc: Int, levelSrc: Int, blockIndexSrc: Vec3i,
             layerDst: Int, faceDst: Int, levelDst: Int, blockIndexDst: Vec3i,
             blockCount: Vec3i) {

        val baseOffsetSrc = storageSrc.baseOffset(layerSrc, faceSrc, levelSrc)
        val baseOffsetDst = baseOffset(layerDst, faceDst, levelDst)
        val imageSrc = MemoryUtil.memAddress(storageSrc.data) + baseOffsetSrc
        val imageDst = MemoryUtil.memAddress(data) + baseOffsetDst

        for (blockIndexZ in 0 until blockCount.z)
            for (blockIndexY in 0 until blockCount.y) {

                val blockIndex = Vec3i(0, blockIndexY, blockIndexZ)
                val offsetSrc = storageSrc.imageOffset(blockIndexSrc + blockIndex, storageSrc.extent(levelSrc)) * storageSrc.blockSize
                val offsetDst = imageOffset(blockIndexDst + blockIndex, extent(levelDst)) * blockSize
                val dataSrc = imageSrc + offsetSrc
                val dataDst = imageDst + offsetDst
                MemoryUtil.memCopy(dataDst, dataSrc, blockSize * blockCount.x)
            }
    }

    fun levelSize(level: Int): Int {
        assert(level in 0 until levels)
        return blockSize * glm.compMul(blockCount(level))
    }

    fun faceSize(baseLevel: Int, maxLevel: Int): Int {

        assert(maxLevel in 0 until levels)
        assert(baseLevel in 0 until levels)
        assert(baseLevel <= maxLevel)
        // The size of a face is the sum of the size of each level.
        return (baseLevel..maxLevel).sumBy { levelSize(it) }
    }

    fun layerSize(baseFace: Int, maxFace: Int, baseLevel: Int, maxLevel: Int): Int {

        assert(maxFace in 0 until faces)
        assert(baseFace in 0 until faces)
        assert(maxLevel in 0 until levels)
        assert(baseLevel in 0 until levels)
        // The size of a layer is the sum of the size of each face. All the faces have the same size.
        return faceSize(baseLevel, maxLevel) * (maxFace - baseFace + 1)
    }

    fun destroy() = data?.destroy()

    override fun equals(other: Any?): Boolean {
        return if (other !is Storage) false
        else layers == other.layers &&
                faces == other.faces &&
                levels == other.levels &&
                blockSize == other.blockSize &&
                blockCount == other.blockCount &&
                blockExtend == other.blockExtend &&
                extent == other.extent &&
                data == other.data
    }
}