import { useState, createContext } from "react";

// 1) Initial state with default values
const initialState = {
  toys: [], // Array to hold toy data
  setToys: () => {}, // Empty function for now, will be updated later
};

// 2) Create the context
const ToyStoreContext = createContext(initialState);

// 3) Create the wrapper component
const ToyStoreContextProvider = (props) => {
  // State variable for storing toy data
  const [toys, setToys] = useState([]);

  // Return the ToyStoreContextProvider with ToyStoreContext.Provider
  return (
    <>
      <ToyStoreContext.Provider value={{ toys, setToys }}>
        {props.children}
      </ToyStoreContext.Provider>
    </>
  );
};

// Export the ToyStoreContextProvider and ToyStoreContext
export { ToyStoreContextProvider, ToyStoreContext };
export default ToyStoreContext;
