package com.foreverrafs.numericals.ui.conversions

import com.foreverrafs.core.Numericals
import com.foreverrafs.core.exceptions.NotABinaryException
import com.foreverrafs.numericals.R
import timber.log.Timber

/**
 * Created by Aziz Rafsanjani on 11/4/2017.
 */
class FragmentBinToDec : ConversionsBase() {
    override fun initControls() {
        super.initControls()
        setHeader(getString(R.string.bin_to_decimal))
        setDescription(getString(R.string.bin_to_dec_desc))
        setInputHint(getString(R.string.binary_input_hint))
        setMethodName("bintodec")
    }

    override fun onCalculate() {
        val decimal: String

        val binary = etInput.text.toString()
        if (binary.isEmpty()) {
            showErrorMessage("Cannot be empty", false)
            return
        }
        if (binary.toDouble() <= 0) {
            showErrorMessage("Must be greater than 0!", false)
            return
        }
        if (binary.length >= 24) {
            showErrorMessage("Below 24 bits please (::)", false)
        }
        try {
            decimal = Numericals.binaryToDecimal(binary).toString()
            tvAnswer.text = decimal
            displayAnswer()
        } catch (ex: NotABinaryException) {
            Timber.e(ex)
            showErrorMessage(ex.message, true)
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }
}