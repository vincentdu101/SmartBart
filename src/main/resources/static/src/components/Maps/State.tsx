import * as React from "react";
import { IStateProps, IStateState } from "../../types/MapTypes";

export class State extends React.Component<IStateProps, IStateState> {

    constructor(props: any) {
        super(props);
        this.onInteractionHandler = this.onInteractionHandler.bind(this);
    }

    private onInteractionHandler(): void {
        // const { stateName } = this.props;
    }

    public render(): JSX.Element {
        const { mapType, feature, path, radius, fill, i } = this.props;
            console.log(mapType);
            console.log(feature);
            console.log(radius);
        // if (mapType === "choropleth") {
            return (
                <path
                    className={`states state-transition-${i}`}
                    d={path}
                    fill={fill}
                    stroke="#FFFFFF"
                    strokeWidth={0.25}
                    onMouseEnter={this.onInteractionHandler}
                    onClick={this.onInteractionHandler}
                    onTouchStart={this.onInteractionHandler}
                />
            );
        // }
    }


}