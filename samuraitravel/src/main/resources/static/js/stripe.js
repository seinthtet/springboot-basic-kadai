const stripe = Stripe('pk_test_51RP5tV4SQpbvalq68nuYHmVBUX0E2UaanwzCS5Kv0FEoelDGRIpEQVfGzfX1iesodWwqtyebGU3bvTWY39Xade4000rwoG1emY');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
 stripe.redirectToCheckout({
   sessionId: sessionId
 })
});