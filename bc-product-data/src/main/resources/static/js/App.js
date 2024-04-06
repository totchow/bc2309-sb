
import { computed, ref, watchEffect } from "vue";

import axios from 'axios'
export default {
  name: "App",
  setup() {
    const products = ref([]);
    const cloneProducts = ref([]);
    const search = ref('')
    // api call
    const retrieveProducts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8092/data/api/v1/product" // change port
        );
        products.value = response.data.data; //.data.data (ApiResponse.data).data
      } catch (err) {
        console.log(err);
      }
    };
    // fetch timer, invoke backend service in every 30 seconds 
    setInterval(() => {
      retrieveProducts();
    }, 30000);
    // 10 items for marquee
    const tenProducts = computed(() => {
      return products.value.slice(0, 10);
    });
    // cloneCoins
    watchEffect(()=>{
      const dup = products.value.slice(0, 5);
      cloneProducts.value = dup;
    });
    // search
    const matchedNames = computed(()=> {
      return products.value.filter((product) => product.productId.includes(search.value));
    });
    return { tenProducts, cloneProducts, matchedNames, search };
  },
};

