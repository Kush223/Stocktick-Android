import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEquityDetailsBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.EquityViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.fragments.EquityDetialsArgs
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.fragments.EquityDetialsDirections
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.EquityModel

class EquityDetails : Fragment( R.layout.fragment_equity_details) {

    private lateinit var binding: FragmentEquityDetailsBinding
    private val viewModel : EquityViewModel by activityViewModels()

    private val args: EquityDetialsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding = FragmentEquityDetailsBinding.bind(view)
        val details : EquityModel? = try {
            viewModel.getElement(
                position = args.position
            )
        } catch (e: IndexOutOfBoundsException){
            null
        }
        if (details == null) navController.navigateUp()

        binding.fabEdit.setOnClickListener{
            val action = EquityDetialsDirections.actionEquityDetialsToEquityDetailsEdit(args.position)
            view.findNavController().navigate(action)
        }

        //autogenerated
        if (details!!.broker_name.isEmpty()){
            binding.brokerName.visibility = View.GONE
            binding.brokerNameLabel.visibility = View.GONE}
        else {
            binding.brokerName.text = details.broker_name
        }

        if (details!!.account_number.isEmpty()){
            binding.accountNumber.visibility = View.GONE
            binding.accountNumberLabel.visibility = View.GONE}
        else {
            binding.accountNumber.text = details.account_number
        }

        if (details!!.instrument_name.isEmpty()){
            binding.instrumentName.visibility = View.GONE
            binding.instrumentNameLabel.visibility = View.GONE}
        else {
            binding.instrumentName.text = details.instrument_name
        }

        if (details!!.qty.isEmpty()){
            binding.qty.visibility = View.GONE
            binding.qtyLabel.visibility = View.GONE}
        else {
            binding.qty.text = details.qty
        }

        if (details!!.purchase_price.isEmpty()){
            binding.purchasePrice.visibility = View.GONE
            binding.purchasePriceLabel.visibility = View.GONE}
        else {
            binding.purchasePrice.text = details.purchase_price
        }

        if (details!!.purchase_value.isEmpty()){
            binding.purchaseValue.visibility = View.GONE
            binding.purchaseValueLabel.visibility = View.GONE}
        else {
            binding.purchaseValue.text = details.purchase_value
        }

        if (details!!.market_price.isEmpty()){
            binding.marketPrice.visibility = View.GONE
            binding.marketPriceLabel.visibility = View.GONE}
        else {
            binding.marketPrice.text = details.market_price
        }

        if (details!!.market_value.isEmpty()){
            binding.marketValue.visibility = View.GONE
            binding.marketValueLabel.visibility = View.GONE}
        else {
            binding.marketValue.text = details.market_value
        }

        if (details!!.nominee_name.isEmpty()){
            binding.nomineeName.visibility = View.GONE
            binding.nomineeNameLabel.visibility = View.GONE}
        else {
            binding.nomineeName.text = details.nominee_name
        }

        if (details!!.relationship.isEmpty()){
            binding.relationship.visibility = View.GONE
            binding.relationshipLabel.visibility = View.GONE}
        else {
            binding.relationship.text = details.relationship
        }

        if (details!!.allocation_percent.isEmpty()){
            binding.allocationPercent.visibility = View.GONE
            binding.allocationPercentLabel.visibility = View.GONE}
        else {
            binding.allocationPercent.text = details.allocation_percent
        }

        if (details!!.nominee_name2_.isEmpty()){
            binding.nomineeName2.visibility = View.GONE
            binding.nomineeName2Label.visibility = View.GONE}
        else {
            binding.nomineeName2.text = details.nominee_name2_
        }

        if (details!!.relationship2.isEmpty()){
            binding.Relationship2.visibility = View.GONE
            binding.Relationship2Label.visibility = View.GONE}
        else {
            binding.Relationship2.text = details.relationship2
        }

        if (details!!.allocation_percent2.isEmpty()){
            binding.allocationPercent2.visibility = View.GONE
            binding.allocationPercent2Label.visibility = View.GONE}
        else {
            binding.allocationPercent2.text = details.allocation_percent2
        }

    }
}