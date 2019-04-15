package com.example.besteventslviv.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.besteventslviv.Database.AppDatabase
import com.example.besteventslviv.Database.Entities.User
import com.example.besteventslviv.R
import com.example.besteventslviv.StaticCache
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var isSignIn: Boolean = true
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        appDatabase = AppDatabase.getAppDatabase(this.baseContext)!!

        sign_in.setOnClickListener { _ -> setSignIn() }

        sign_up.setOnClickListener { _ -> setSignUp() }

        login_continue_button.setOnClickListener { v ->

            if (login.text.isNullOrEmpty()) {
                showToasrt(R.string.error_login_required.toString())
                return@setOnClickListener
            }

            if (password.text.isNullOrEmpty()) {
                showToasrt(R.string.error_password_required.toString())
                return@setOnClickListener
            }

            if (isSignIn) {
                if (!checkUserExists()) {
                    showToasrt(R.string.error_login_not_exists.toString())
                    return@setOnClickListener
                }

                if (!checkIsPasswordCorrect()) {
                    showToasrt(R.string.error_incorrect_password.toString())
                    return@setOnClickListener
                }

                StaticCache.UserID = appDatabase.getUsersDao().getUserByLogin(login.text.toString())!!.ID

                val intent = Intent(this, GroupsActivity::class.java)
                startActivity(intent)
            } else {
                if (passwordConfirm.text.isNullOrEmpty()) {
                    showToasrt(R.string.error_password_confirm_required.toString())
                    return@setOnClickListener
                }

                if (!checkUserExists()) {
                    showToasrt(R.string.error_login_exists.toString())
                    return@setOnClickListener
                }

                if (!checkPasswordsAreTheSame()) {
                    showToasrt(R.string.error_passwords_are_not_the_same.toString())
                    return@setOnClickListener
                }

                var newUser = User(
                    ID = 0,
                    Login = login.text.toString(),
                    Password = password.text.toString(),
                    Email = email.text.toString()
                )

                StaticCache.UserID = appDatabase.getUsersDao().Insert(newUser)

                val intent = Intent(this, GroupsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setSignIn() {
        sign_in.setBackgroundColor(resources.getColor(R.color.Transparent))
        sign_up.setBackgroundColor(R.drawable.button_bottom_border)
        emailLayout.visibility = View.GONE
        passwordConfirmLayout.visibility = View.GONE
        isSignIn = true
    }

    private fun setSignUp() {
        sign_up.setBackgroundColor(resources.getColor(R.color.Transparent))
        sign_in.setBackgroundColor(R.drawable.button_bottom_border)
        emailLayout.visibility = View.VISIBLE
        passwordConfirmLayout.visibility = View.VISIBLE
        isSignIn = false
    }

    private fun checkUserExists(): Boolean {
        val dao = appDatabase.getUsersDao()
        var user = dao.getUserByLogin(login.text.toString())
        return user == null
    }

    private fun checkIsPasswordCorrect(): Boolean {
        val dao = appDatabase.getUsersDao()
        var user = dao.getUserByLogin(login.text.toString())
        return user != null && user.Password == password.text.toString()
    }

    private fun checkPasswordsAreTheSame(): Boolean {
        return password.text == passwordConfirm.text
    }

    private fun showToasrt(message: String){
        Toast.makeText(this.baseContext, message, Toast.LENGTH_SHORT).show()
    }
}
