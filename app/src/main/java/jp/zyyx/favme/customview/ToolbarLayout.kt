package jp.zyyx.favme.customview

import jp.zyyx.favme.R

open class ToolbarLayout : LinearLayout {
    ​
    private val v: View by lazy { LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true) }
    val tvTitle: MaterialTextView by lazy { v.findViewById<MaterialTextView>(R.id.tv_title) }
    val tvRight: MaterialTextView by lazy { v.findViewById<MaterialTextView>(R.id.tv_right) }
    val ivLeft: AppCompatImageView by lazy { v.findViewById<AppCompatImageView>(R.id.iv_left) }
    val ivRight: AppCompatImageView by lazy { v.findViewById<AppCompatImageView>(R.id.iv_right) }
    ​
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }
    ​
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }
    ​
    private fun init(attrs: AttributeSet) {
        orientation = HORIZONTAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarLayout
        )
        try {
            val icLeft = a.getDrawable(R.styleable.ToolbarLayout_icLeft)
            if (icLeft != null) {
                ivLeft.setImageDrawable(icLeft)
                ivLeft.visible()
            } else {
                ivLeft.invisible()
            }
            val icRight = a.getDrawable(R.styleable.ToolbarLayout_icRight)
            if (icRight != null) {
                ivRight.visible()
                ivRight.setImageDrawable(icRight)
                tvRight.gone()
            } else {
                ivRight.gone()
            }
            ​
            val textTitle = a.getString(R.styleable.ToolbarLayout_textTitle) ?: ""
            tvTitle.text = textTitle
            ​
            val textRight = a.getString(R.styleable.ToolbarLayout_textRight) ?: ""
            ​
            if (textRight.isNotBlank()) {
                tvRight.text = textRight
                tvRight.visible()
            } else {
                tvRight.gone()
            }
//            tvRight.paintFlags = tvRight.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        } finally {
            a.recycle()
        }
        Insetter.builder()
            .padding(windowInsetTypesOf(statusBars = true))
            .applyToView(this)
    }
}