package jp.zyyx.favme.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ScreenType : Parcelable {

    abstract val name: String

    sealed class SplashFlow : ScreenType() {

        @Parcelize
        object Splash : SplashFlow() {
            override val name: String
                get() = "Splash Fragment"
        }

        @Parcelize
        object Introduce : SplashFlow() {
            override val name: String
                get() = "Introduce Fragment"
        }

    }

    sealed class AuthFlow : ScreenType() {

        @Parcelize
        object Login : AuthFlow() {
            override val name: String
                get() = "Login Fragment"
        }

        @Parcelize
        object LoginOrRegister : AuthFlow() {
            override val name: String
                get() = "Login Or Register Fragment"
        }

        @Parcelize
        object Register : AuthFlow() {
            override val name: String
                get() = "Register Fragment"
        }

        @Parcelize
        object ForgotPass : AuthFlow() {
            override val name: String
                get() = "Forgot Pass"
        }



        @Parcelize
        object PolicyAndRule : AuthFlow() {
            override val name: String
                get() = "Policy and rule"
        }
    }


    sealed class AccountFlow : ScreenType() {
        @Parcelize
        object InformationSettings : AccountFlow() {
            override val name: String
                get() = "Information Settings"
        }
    }

    sealed class HomeFlow : ScreenType() {
        @Parcelize
        object SearchFragment : HomeFlow() {
            override val name: String
                get() = "Search Fragment"
        }

        @Parcelize
        object HomeFragment : HomeFlow() {
            override val name: String
                get() = "Home Fragment"
        }
        @Parcelize
        object MainFragment : HomeFlow() {
            override val name: String
                get() = "Main Fragment"
        }

        @Parcelize
        object ListDepartmentFragment : HomeFlow() {
            override val name: String
                get() = "List Department Fragment"
        }
    }
}
