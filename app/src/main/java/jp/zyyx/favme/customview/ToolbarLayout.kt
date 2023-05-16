//package jp.zyyx.favme.customview
//
//import android.content.Context
//import android.os.Parcel
//import android.os.Parcelable
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.LinearLayout
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.core.content.ContextCompat
//import com.google.android.material.textview.MaterialTextView
//import jp.zyyx.favme.R
//import jp.zyyx.favme.extension.gone
//import jp.zyyx.favme.extension.invisible
//import jp.zyyx.favme.extension.visible
//
//open class ToolbarLayout : LinearLayout {
//
//    private val v: View by lazy { LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true) }
//    val tvTitle: MaterialTextView by lazy { v.findViewById<MaterialTextView>(R.id.tv_title) }
//    val tvRight: MaterialTextView by lazy { v.findViewById<MaterialTextView>(R.id.tv_right) }
//    val ivLeft: AppCompatImageView by lazy { v.findViewById<AppCompatImageView>(R.id.iv_left) }
//    val ivRight: AppCompatImageView by lazy { v.findViewById<AppCompatImageView>(R.id.iv_right) }
//
//    constructor(parcel: Parcel) : this() {
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init(attrs)
//    }
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        init(attrs)
//    }
//    private fun init(attrs: AttributeSet) {
//        orientation = HORIZONTAL
//        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
//        val a = context.obtainStyledAttributes(
//            attrs,
//            R.styleable.ToolbarLayout
//        )
//        try {
//            val icLeft = a.getDrawable(R.styleable.ToolbarLayout_icLeft)
//            if (icLeft != null) {
//                ivLeft.setImageDrawable(icLeft)
//                ivLeft.visible()
//            } else {
//                ivLeft.invisible()
//            }
//            val icRight = a.getDrawable(R.styleable.ToolbarLayout_icRight)
//            if (icRight != null) {
//                ivRight.visible()
//                ivRight.setImageDrawable(icRight)
//                tvRight.gone()
//            } else {
//                ivRight.gone()
//            }
//            val textTitle = a.getString(R.styleable.ToolbarLayout_textTitle) ?: ""
//            tvTitle.text = textTitle
//            val textRight = a.getString(R.styleable.ToolbarLayout_textRight) ?: ""
//            if (textRight.isNotBlank()) {
//                tvRight.text = textRight
//                tvRight.visible()
//            } else {
//                tvRight.gone()
//            }
////            tvRight.paintFlags = tvRight.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//        } finally {
//            a.recycle()
//        }
//        Insetter.builder()
//            .padding(windowInsetTypesOf(statusBars = true))
//            .applyToView(this)
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<ToolbarLayout> {
//        override fun createFromParcel(parcel: Parcel): ToolbarLayout {
//            return ToolbarLayout(parcel)
//        }
//
//        override fun newArray(size: Int): Array<ToolbarLayout?> {
//            return arrayOfNulls(size)
//        }
//    }
//}