import { createContext, useState } from "react";

// 1) Initial state with default values
const initialState = {
  darkMode: false,
  toggleDarkMode: () => {}, // Empty function for now, will be updated later
};

// 2) Create the context
const DarkModeContext = createContext();

// 3) Create the wrapper component
const DarkModeContextWrapper = (props) => {
  // Props
  const [darkMode, setDarkMode] = useState(false); // State variable for dark mode

  // Method to toggle dark mode
  const toggleDarkMode = () => {
    setDarkMode((mode) => !mode); // Use functional update to toggle the value
  };

  // Return the DarkModeContextProvider with DarkModeContext.Provider
  return (
    <>
      <DarkModeContext.Provider value={{ darkMode, toggleDarkMode }}>
        {props.children}
      </DarkModeContext.Provider>
    </>
  );
};

// Export the DarkModeContext and DarkModeContextWrapper
export { DarkModeContext, DarkModeContextWrapper };
export default DarkModeContext;
