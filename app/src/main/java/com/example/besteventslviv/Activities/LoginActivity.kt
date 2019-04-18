package com.example.besteventslviv.Activities

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Entities.Group
import com.example.besteventslviv.Database.Entities.User
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache
import kotlinx.android.synthetic.main.activity_login.*
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.besteventslviv.Database.Entities.Event
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*


class LoginActivity : AppCompatActivity() {

    private var isSignIn: Boolean = true
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appDatabase = AppDatabase.getAppDatabase(this.baseContext)!!

        sign_in.setOnClickListener { _ -> setSignInView() }

        sign_up.setOnClickListener { _ -> setSignUpView() }

        login_continue_button.setOnClickListener { _ -> logIn() }
    }

    private fun setSignInView() {
        sign_up.setBackgroundColor(getColor(R.color.Transparent))
        sign_in.background = getDrawable(R.drawable.button_bottom_border)
        emailLayout.visibility = View.GONE
        passwordConfirmLayout.visibility = View.GONE
        isSignIn = true
    }

    private fun setSignUpView() {
        sign_in.setBackgroundColor(getColor(R.color.Transparent))
        sign_up.background = getDrawable(R.drawable.button_bottom_border)
        emailLayout.visibility = View.VISIBLE
        passwordConfirmLayout.visibility = View.VISIBLE
        isSignIn = false
    }

    private fun logIn() {
        if (login.text.isNullOrEmpty()) {
            showToasrt(getString(R.string.error_login_required))
            return
        }

        if (password.text.isNullOrEmpty()) {
            showToasrt(getString(R.string.error_password_required))
            return
        }

        if (isSignIn) {
            if (!checkUserExists()) {
                showToasrt(getString(R.string.error_login_not_exists))
                return
            }

            if (!checkIsPasswordCorrect()) {
                showToasrt(getString(R.string.error_incorrect_password))
                return
            }

            StaticCache.UserID = appDatabase.getUsersDao().getUserByLogin(login.text.toString())!!.ID

            val intent = Intent(this, GroupsActivity::class.java)
            startActivity(intent)
        } else {
            if (passwordConfirm.text.isNullOrEmpty()) {
                showToasrt(getString(R.string.error_password_confirm_required))
                return
            }

            if (checkUserExists()) {
                showToasrt(getString(R.string.error_login_exists))
                return
            }

            if (!checkPasswordsAreTheSame()) {
                showToasrt(getString(R.string.error_passwords_are_not_the_same))
                return
            }

            var newUser = User(
                Login = login.text.toString(),
                Password = password.text.toString(),
                Email = email.text.toString()
            )

            StaticCache.UserID = appDatabase.getUsersDao().Insert(newUser).toInt()

            val intent = Intent(this, GroupsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUserExists(): Boolean {
        val dao = appDatabase.getUsersDao()
        val user = dao.getUserByLogin(login.text.toString())
        return user != null
    }

    private fun checkIsPasswordCorrect(): Boolean {
        val dao = appDatabase.getUsersDao()
        val user = dao.getUserByLogin(login.text.toString())
        return user != null && user.Password == password.text.toString()
    }

    private fun checkPasswordsAreTheSame(): Boolean {
        return password.text.toString() == passwordConfirm.text.toString()
    }

    private fun showToasrt(message: String){
        Toast.makeText(this.baseContext, message, Toast.LENGTH_SHORT).show()
    }
}
