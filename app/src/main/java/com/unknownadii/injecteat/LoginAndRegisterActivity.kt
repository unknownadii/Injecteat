package com.unknownadii.injecteat

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.snackbar.Snackbar
import com.unknownadii.injecteat.Utils.SharedPreferenceClass
import com.unknownadii.injecteat.Utils.UtilService
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class LoginAndRegisterActivity : AppCompatActivity() {
    lateinit var textRegister: TextView
    lateinit var textWelcome: TextView
    lateinit var textBuddy: TextView
    lateinit var loginButton: TextView
    lateinit var registerButton: TextView
    lateinit var inputname: androidx.appcompat.widget.AppCompatEditText
    lateinit var inputemail: androidx.appcompat.widget.AppCompatEditText
    lateinit var inputpassword: androidx.appcompat.widget.AppCompatEditText
    lateinit var inputName: com.google.android.material.textfield.TextInputLayout
    lateinit var dialog: Dialog
    lateinit var utilService: UtilService
    lateinit var sharedPreferenceClass: SharedPreferenceClass
    lateinit var loginOrRegister: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_register)
        //toolbar id
        val toolbar =
            findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarLoginAndRegistration)
        //for showing toolbar
        setSupportActionBar(toolbar)
        // for getting back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        utilService = UtilService()
        sharedPreferenceClass = SharedPreferenceClass(this)


        val strRegister = intent.getStringExtra("Register");
        val strLogin = intent.getStringExtra("Login");

        textRegister = findViewById<TextView>(R.id.textRegister)
        textWelcome = findViewById<TextView>(R.id.textWelcome)
        textBuddy = findViewById<TextView>(R.id.textBuddy)
        textBuddy = findViewById<TextView>(R.id.textBuddy)
        loginButton = findViewById<TextView>(R.id.loginButtonInLogAndRegisterActivity)
        registerButton = findViewById<TextView>(R.id.registerButtonInLogAndRegisterActivity)
        inputName = findViewById(R.id.inputName)
        inputname = findViewById(R.id.inputname_et)
        inputemail = findViewById(R.id.email_et)
        inputpassword = findViewById(R.id.password_et)

        loginUser()
        if (!strRegister.isNullOrBlank()) {
            registerUser()

        } else if (!strLogin.isNullOrBlank()) {
            loginUser()
        }

        loginButton.setOnClickListener {
            loginUser()
        }
        registerButton.setOnClickListener {
            registerUser()
        }


        val moveButton = findViewById<ImageView>(R.id.moveForward);
        moveButton.setOnClickListener {
            showDialog()
            utilService.hideKeyboard(it, this)
            if (loginOrRegister == "Login") {
                if (validateLogin(it)) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            } else if (loginOrRegister == "Register") {
                if (validateRegister(it)) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            }
            hideDialog()
        }

    }

    private fun loginUser() {
        textRegister.visibility = View.GONE;
        textWelcome.visibility = View.VISIBLE;
        textBuddy.visibility = View.VISIBLE;
        loginButton.visibility = View.GONE;
        registerButton.visibility = View.VISIBLE;
        inputName.visibility = View.GONE;
        loginOrRegister = "Login"
        inputemail.text?.clear()
        inputpassword.text?.clear()
        inputname.text?.clear()

    }

    private fun registerUser() {
        textRegister.visibility = View.VISIBLE;
        textWelcome.visibility = View.GONE;
        textBuddy.visibility = View.GONE;
        loginButton.visibility = View.VISIBLE;
        registerButton.visibility = View.GONE;
        inputName.visibility = View.VISIBLE;
        loginOrRegister = "Register"
        inputemail.text?.clear()
        inputpassword.text?.clear()
        inputname.text?.clear()

    }

    private fun registerPatient(view: View) {
        showDialog()
        val url = "https://letstreat.herokuapp.com/user/patient/register"

        val params: HashMap<String, String> = HashMap()
        params["email"] = inputemail.text.toString()
        params["name"] = inputname.text.toString()
        params["password"] = inputpassword.text.toString()

        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, JSONObject(params as Map<*, *>?), Response.Listener { response ->
                try {
                    if (response.getBoolean("success") == true) {
                        inputemail.text?.clear()
                        inputpassword.text?.clear()
                        inputname.text?.clear()
                        val token = response.getString("token")
                        sharedPreferenceClass.setValue_string("token", token)
                        val msg = response.getString("message")
                        utilService.showSnackBar(view, msg)
                        startActivity(Intent(this, MainActivity::class.java))
                        hideDialog()
                        finishAffinity()
                    } else if (response.getBoolean("success") == false) {
                        val msg = response.getString("message")
                        utilService.showSnackBar(view, msg)
                        hideDialog()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                val response = error.networkResponse
                if (error is ServerError && response != null) {
                    try {
                        utilService.showSnackBar(view, "Please try again")
                        hideDialog()
                    } catch (je: JSONException) {
                        je.printStackTrace()
                        hideDialog()
                    } catch (je: UnsupportedEncodingException) {
                        je.printStackTrace()
                        hideDialog()
                    }
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: HashMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                return params
            }
        }

        // set retry policy
        // set retry policy
        val socketTime = 3000
        val policy: RetryPolicy = DefaultRetryPolicy(
            socketTime,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        jsonObjectRequest.retryPolicy = policy


        HomeSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    private fun loginPatient(view: View) {
        showDialog()
        val url = "https://letstreat.herokuapp.com/user/patient/login"

        val params: HashMap<String, String> = HashMap()
        params["email"] = inputemail.text.toString()
        params["password"] = inputpassword.text.toString()

        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, JSONObject(params as Map<*, *>?), Response.Listener { response ->
                try {
                    if (response.getBoolean("success") == true) {
                        Snackbar.make(view, "Login Successful", Snackbar.LENGTH_LONG).show();
                        inputemail.text?.clear()
                        inputpassword.text?.clear()
                        inputname.text?.clear()
                        val token = response.getString("token")
                        sharedPreferenceClass.setValue_string("token", token)
                        val msg = response.getString("message")
                        utilService.showSnackBar(view, msg)
                        startActivity(Intent(this, MainActivity::class.java))
                        hideDialog()
                        finishAffinity()
                    } else if (response.getBoolean("success") == false) {
                        val msg = response.getString("message")
                        utilService.showSnackBar(view, msg)
                        hideDialog()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                val response = error.networkResponse
                if (error is ServerError && response != null) {
                    try {
                        utilService.showSnackBar(view, "Please try again")
                        hideDialog()
                    } catch (je: JSONException) {
                        je.printStackTrace()
                        hideDialog()
                    } catch (je: UnsupportedEncodingException) {
                        je.printStackTrace()
                        hideDialog()
                    }
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: HashMap<String, String> = HashMap()
                headers["Content-Type"] = "application/json"
                return params
            }
        }

        // set retry policy
        val socketTime = 3000
        val policy: RetryPolicy = DefaultRetryPolicy(
            socketTime,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        jsonObjectRequest.retryPolicy = policy


        HomeSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun hideDialog() {
        dialog.dismiss()
    }

    private fun validateRegister(view: View): Boolean {
        val isValid: Boolean
        isValid = if (!inputemail.text.isNullOrBlank()) {
            if (!inputpassword.text.isNullOrBlank()) {
                if (!inputname.text.isNullOrBlank()) {
                    true
                } else {
                    utilService.showSnackBar(view, "please enter name....")
                    false
                }
            } else {
                utilService.showSnackBar(view, "please enter password....")
                false
            }
        } else {
            utilService.showSnackBar(view, "please enter email....")
            false
        }
        return isValid
    }

    private fun validateLogin(view: View): Boolean {
        val isValid: Boolean
        isValid = if (!inputemail.text.isNullOrBlank()) {
            if (!inputpassword.text.isNullOrBlank()) {
                true
            } else {
                utilService.showSnackBar(view, "Please enter password....")
                false
            }
        } else {
            utilService.showSnackBar(view, "Please enter email....")
            false
        }
        return isValid
    }

    //for working back arrow on toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}