package kitttn.cmindtesttask.views.base

import android.support.v4.app.Fragment
import kitttn.cmindtesttask.views.MainActivity

/**
 * @author kitttn
 */

open class BaseFragment : Fragment() {
    val act by lazy { activity as MainActivity }
}