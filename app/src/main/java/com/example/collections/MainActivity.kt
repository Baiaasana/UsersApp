package com.example.collections

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.collections.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
        binding.txvActive.text = "0"
        binding.txvRemoved.text = "0"
    }

    private fun listeners() {

        binding.btnAdd.setOnClickListener {

            when (false) {
                (!isEmptyField() && !checkIfNumberIsInteger()) ->
                    Toast.makeText(this, getString(R.string.emtpy_fields_error), Toast.LENGTH_SHORT)
                        .show()
                !checkIfNumberIsInteger() ->
                    Toast.makeText(this, getString(R.string.age_number_error), Toast.LENGTH_SHORT)
                        .show()
                isValidEmail() ->
                    Toast.makeText(
                        this,
                        getString(R.string.invalid_email_error),
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    val firstName = binding.edtFirstName.text.toString()
                    val lastName = binding.edtLastName.text.toString()
                    val age = binding.edtAge.text.toString().toInt()
                    val email = binding.edtEmail.text.toString()
                    val userInfo = User(firstName, lastName, age, email)
                    addUser(userInfo)
                }
            }
        }

        binding.btnRemove.setOnClickListener {

            when (false) {
                (!isEmptyField() && !checkIfNumberIsInteger()) ->
                    Toast.makeText(this, getString(R.string.emtpy_fields_error), Toast.LENGTH_SHORT)
                        .show()
                !checkIfNumberIsInteger() ->
                    Toast.makeText(this, getString(R.string.age_number_error), Toast.LENGTH_SHORT)
                        .show()
                isValidEmail() ->
                    Toast.makeText(
                        this,
                        getString(R.string.invalid_email_error),
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    val firstName = binding.edtFirstName.text.toString()
                    val lastName = binding.edtLastName.text.toString()
                    val age = binding.edtAge.text.toString().toInt()
                    val email = binding.edtEmail.text.toString()
                    val userInfo = User(firstName, lastName, age, email)
                    removeUser(userInfo)
                }
            }
        }

        binding.btnUpdate.setOnClickListener {

            when (false) {
                (!isEmptyField() && !checkIfNumberIsInteger()) ->
                    Toast.makeText(this, getString(R.string.emtpy_fields_error), Toast.LENGTH_SHORT)
                        .show()
                !checkIfNumberIsInteger() ->
                    Toast.makeText(this, getString(R.string.age_number_error), Toast.LENGTH_SHORT)
                        .show()
                isValidEmail() ->
                    Toast.makeText(
                        this,
                        getString(R.string.invalid_email_error),
                        Toast.LENGTH_SHORT
                    ).show()
                else -> {
                    val firstName = binding.edtFirstName.text.toString()
                    val lastName = binding.edtLastName.text.toString()
                    val age = binding.edtAge.text.toString().toInt()
                    val email = binding.edtEmail.text.toString()
                    val userInfo = User(firstName, lastName, age, email)
                    updateUser(userInfo)
                }
            }
        }
    }

    private fun addUser(userData: User) {

        if (userList.isEmpty()) {
            userList.add(userData)
            clearFields()
            increaseActiveUsers()
            binding.txvResult.text = getString(R.string.add_successfully)
            binding.txvResult.setTextColor(Color.GREEN)

        } else {
            for (user in userList) {
                if (user == userData) {
                    binding.txvResult.text = getString(R.string.already_exists)
                    binding.txvResult.setTextColor(Color.RED)
                } else {
                    userList.add(userData)
                    clearFields()
                    increaseActiveUsers()
                    binding.txvResult.text = getString(R.string.add_successfully)
                    binding.txvResult.setTextColor(Color.GREEN)
                }
            }
        }
        Log.d("add", userList.toString())
    }

    private fun removeUser(userData: User) {

        if (userList.isEmpty()) {
            binding.txvResult.text = getString(R.string.not_exists)
            binding.txvResult.setTextColor(Color.RED)
        } else {
            for (user in userList) {
                if (user == userData) {
                    userList.remove(user)
                    clearFields()
                    increaseRemovedUsers()
                    binding.txvResult.text = getString(R.string.removed_successfully)
                    binding.txvResult.setTextColor(Color.GREEN)
                } else {
                    binding.txvResult.text = getString(R.string.not_exists)
                    binding.txvResult.setTextColor(Color.RED)
                }
            }
        }
        Log.d("remove", userList.toString())
    }

    private fun updateUser(userData: User) {

        if (userList.isEmpty()) {
            binding.txvResult.text = getString(R.string.not_exists)
            binding.txvResult.setTextColor(Color.RED)
        } else {
            for (user in userList) {
                if (user.email == userData.email) {
                    user.firstName = userData.firstName
                    user.lastName = userData.lastName
                    user.age = userData.age
                    clearFields()
                    binding.txvResult.text = getString(R.string.updated_successfully)
                    binding.txvResult.setTextColor(Color.GREEN)
                } else {
                    binding.txvResult.text = getString(R.string.not_exists)
                    binding.txvResult.setTextColor(Color.RED)
                }
            }
        }
        Log.d("update", userList.toString())
    }

    private fun isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()

    private fun isEmptyField(): Boolean = with(binding) {
        return@with binding.edtFirstName.text.toString() == "" ||
                binding.edtLastName.text.toString() == "" ||
                binding.edtAge.text.toString() == "" ||
                binding.edtEmail.text.toString() == ""
    }

    private fun checkIfNumberIsInteger(): Boolean = with(binding) {
        try {
            binding.edtAge.toString().toInt()
            return true
        } catch (e: NumberFormatException) {
        }

        try {
            binding.edtAge.toString().toFloat()
            return false
        } catch (e: NumberFormatException) {
        }
        return false
    }

    private fun clearFields() = with(binding) {
        binding.edtFirstName.text?.clear()
        binding.edtLastName.text?.clear()
        binding.edtAge.text?.clear()
        binding.edtEmail.text?.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun increaseActiveUsers() {
        binding.txvActive.text = (binding.txvActive.text.toString().toInt() + 1).toString()
    }

    @SuppressLint("SetTextI18n")
    private fun increaseRemovedUsers() {
        binding.txvRemoved.text = (binding.txvRemoved.text.toString().toInt() + 1).toString()
        binding.txvActive.text = (binding.txvActive.text.toString().toInt() - 1).toString()
    }
}

