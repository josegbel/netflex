package com.example.netflex.video

import com.example.netflex.VideoPlayerController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.AVFoundation.AVLayerVideoGravityResizeAspect
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.currentItem
import platform.AVFoundation.currentTime
import platform.AVFoundation.duration
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.rate
import platform.AVFoundation.seekToTime
import platform.AVFoundation.volume
import platform.CoreGraphics.CGRectMake
import platform.CoreMedia.CMTimeMake
import platform.Foundation.NSURL
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
class IosVideoPlayerController(
    videoUrl: String,
) : VideoPlayerController {
    private val player: AVPlayer = AVPlayer(uRL = NSURL(string = videoUrl))

    private val playerLayer =
        AVPlayerLayer().apply {
            player = this@IosVideoPlayerController.player
            videoGravity = AVLayerVideoGravityResizeAspect
        }

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun seekTo(position: Long) {
        player.seekToTime(CMTimeMake(value = position, timescale = 1000))
    }

    override fun getCurrentPosition(): Long = player.currentTime().useContents { (this.value / this.timescale) * 1000 }

    override fun getDuration(): Long = player.currentItem?.duration()?.useContents { value } ?: 0

    override fun getVolume(): Float = player.volume

    override fun setVolume(volume: Float) {
        player.volume = volume
    }

    override fun isPlaying(): Boolean = player.rate != 0.0.toFloat()

    fun getPlayerView(): UIView =
        object : UIView(CGRectMake(0.0, 0.0, 375.0, 667.0)) {
            init {
                layer.addSublayer(playerLayer)
            }

            override fun layoutSubviews() {
                super.layoutSubviews()
                playerLayer.frame = bounds
            }
        }
}
