import * as React from "react";
import { ITooltipState, ITooltipProps } from "../../types/TooltipTypes";
import "./CircleTooltip.css";

export class CircleTooltip extends React.Component<ITooltipProps, ITooltipState> {

    constructor(props: any) {
        super(props);
        this.state = {
            text: "",
            x: 0,
            y: 0,
            tooltipActive: false
        };
    } 

    public componentDidMount(): void {
        this.setState({});
    }

    private outputTooltipClass(): string {
        return this.state.tooltipActive ? "circle-tooltip active" : "circle-tooltip";
    }

    public render(): JSX.Element {
        return (
            <div className={this.outputTooltipClass()}>
                Test
            </div>
        );
    }

}