// Importing necessary libraries and components
import { InfinitySpin } from "react-loader-spinner";

// Spinner component
const Spinner = (props) => {
  return (
    <>
      {/* Displaying a heading with the text provided via the "text" prop */}
      <h2>{props.text}</h2>

      {/* Rendering the InfinitySpin from react-loader-spinner */}
      <InfinitySpin width="200" color="#4fa94d" />
    </>
  );
};

export default Spinner;
