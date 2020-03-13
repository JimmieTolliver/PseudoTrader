/**
 *
 * @author Jimmie Tolliver
 */

var marketChange = document.getElementById('marketChange');

if (marketChange.innerText.charAt(0) === '-'){
	marketChange.style.color = 'red';
} else{
	marketChange.style.color = 'green';
}