package com.example.androidnodelogin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.login).setOnClickListener {
            handleLoginDialog()
        }

        findViewById<View>(R.id.signup).setOnClickListener {
            handleSignupDialog()
        }
    }

    private fun handleLoginDialog() {
        val view = layoutInflater.inflate(R.layout.login_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()

        val loginBtn = view.findViewById<Button>(R.id.login)
        val emailEdit = view.findViewById<EditText>(R.id.emailEdit)
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit)

        loginBtn.setOnClickListener {
            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()

            viewModel.login(email, password) { result ->
                if (result != null) {
                    AlertDialog.Builder(this)
                        .setTitle(result.name)
                        .setMessage(result.email)
                        .show()
                } else {
                    Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun handleSignupDialog() {
        val view = layoutInflater.inflate(R.layout.signup_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()

        val signupBtn = view.findViewById<Button>(R.id.signup)
        val nameEdit = view.findViewById<EditText>(R.id.nameEdit)
        val emailEdit = view.findViewById<EditText>(R.id.emailEdit)
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit)

        signupBtn.setOnClickListener {
            val name = nameEdit.text.toString()
            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()

            viewModel.signup(name, email, password) { success ->
                if (!success) {
                    Toast.makeText(this, "Already registered", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
