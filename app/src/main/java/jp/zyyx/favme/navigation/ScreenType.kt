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
        object MainFragment : AuthFlow() {
            override val name: String
                get() = "Main Fragment"
        }
    }


    sealed class AccountFlow : ScreenType() {
        @Parcelize
        object InformationSettings : AccountFlow() {
            override val name: String
                get() = "Information Settings"
        }
    }
}
